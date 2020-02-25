package com.ncov.base.test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.ncov.base.web.NcovSystemApplication;
import com.ncov.base.web.constant.RumorType;
import com.ncov.base.web.dao.NcovInfoDao;
import com.ncov.base.web.model.entity.NcovAreaInfo;
import com.ncov.base.web.model.entity.NcovInfo;
import com.ncov.base.web.model.entity.RumorInfo;
import com.ncov.base.web.service.NcovAreaInfoService;
import com.ncov.base.web.service.RumorInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@SpringBootTest(classes = NcovSystemApplication.class)
@RunWith(SpringRunner.class)
public class RestTest {

    @Autowired
    private RestTemplate restTemplate;

    //1 返回最新数据   0 返回时间序列数据
    public static final String NCOV_CONDTION_OF_CHINA_URL="https://lab.isaaclin.cn/nCoV/api/overall?latest=%s";
    //1. 返回最新数据  0 返回时间序列数据   province省份名称
    public static final String NCON_CONDITION_OF_PROVINCE_URL="https://lab.isaaclin.cn/nCoV/api/area?latest=%s";
    //1. rumorType(0谣言，1可信信息,2.未证实信息)   num条数
    public static final String RUMORS_INFO_URL="https://lab.isaaclin.cn/nCoV/api/rumors?rumorType=%s&num=all";


    @Autowired
    private NcovInfoDao ncovInfoDao;

    @Autowired
    private NcovAreaInfoService ncovAreaInfoService;

    @Autowired
    private RumorInfoService rumorInfoService;

    //获取过去多少天的数据
    private static final Integer LAST_DAYS=2;

    private Date createDate(){
        DateTime dateTime = DateUtil.offsetDay(new Date(), -LAST_DAYS);
        DateTime parse = DateUtil.parse(DateUtil.format(dateTime, "yyyy-MM-dd 00:00:00"));
        return parse;
    }

    /**
     * <pre>
     *      功能描述: 采集今日全国疫情统计数据
     * </pre>
     * @author ChenJunLin
     * @param
     * @return void
     * @date 2020/2/19 20:56
     */
    @Test
    public void getNocvData(){
        long startTime = System.currentTimeMillis();
        String chinaAllInfoUrl=String.format(NCOV_CONDTION_OF_CHINA_URL,1);
        String timeInfoUrl=String.format(NCOV_CONDTION_OF_CHINA_URL,0);
        ResponseEntity<JSONObject> forEntity = restTemplate.getForEntity(timeInfoUrl, JSONObject.class);
        JSONArray results = forEntity.getBody().get("results", JSONArray.class);
        //删除今日数据
        Date dayConfig = createDate();
        long deleteCount = ncovInfoDao.deleteDataOfToday(LAST_DAYS+1);
        AtomicInteger atomicInteger = new AtomicInteger();
        for (Object result:results){
            JSONObject jsonResult=(JSONObject)result;
            NcovInfo ncvoInfo = jsonResult.toBean(NcovInfo.class);
            if (DateUtil.isIn(ncvoInfo.getUpdateTime(),dayConfig,new Date())){
                ncvoInfo.prepareInsert();
                ncovInfoDao.insert(ncvoInfo);
                atomicInteger.incrementAndGet();
            }
            log.info(ncvoInfo.toString());
        }
        //统计数据仓库总量
        Integer dataCount = ncovInfoDao.countNcvoInfo();
        //当前疫情情况
        JSONObject jsonObject =(JSONObject) results.get(0);
        NcovInfo currentData = jsonObject.toBean(NcovInfo.class);
        log.info("今日采集共存入数据:"+atomicInteger.get()+"条,"+"清除今日数据共"+deleteCount+"条");
        log.info("数据库已采集总量:"+dataCount+"条,当前获取数据量:"+results.size());
        log.info("数据最新更新时间:"+DateUtil.format(currentData.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
        log.info("确诊总数:"+currentData.getCurrentConfirmedCount()+",较昨日:"+currentData.getConfirmedIncr());
        log.info("疑似病例:"+currentData.getSuspectedCount()+",较昨日:"+currentData.getSuspectedIncr());
        log.info("重症数:"+currentData.getSeriousCount()+",较昨日:"+currentData.getSeriousIncr());
        log.info("死亡总数:"+currentData.getDeadCount()+",较昨日:"+currentData.getDeadIncr());
        log.info("治愈总数:"+currentData.getCuredCount()+",较昨日:"+currentData.getCuredIncr());
        log.info("数据处理耗时:{}",(System.currentTimeMillis()-startTime));
    }

    @Test
    public void getNcovAreaInfo(){
        long startTime = System.currentTimeMillis();
        String url=String.format(NCON_CONDITION_OF_PROVINCE_URL,0);
        ResponseEntity<JSONObject> forEntity = restTemplate.getForEntity(url, JSONObject.class);
        JSONArray results = forEntity.getBody().getJSONArray("results");
        AtomicInteger boardCount = new AtomicInteger();
        AtomicInteger boatCount = new AtomicInteger();
        AtomicInteger domesticCount = new AtomicInteger();
        AtomicInteger koreaCount = new AtomicInteger();
        AtomicInteger totalCount = new AtomicInteger();
        Integer startCount = ncovAreaInfoService.countData();
        Set<String> countrySet=new HashSet<>();
        Date startDate = createDate();
        long deleteCount = ncovAreaInfoService.deleteDataOfToday(LAST_DAYS+1);
        for (Object result:results){
            JSONObject jsonObject =(JSONObject) result;
            NcovAreaInfo parent = jsonObject.toBean(NcovAreaInfo.class);
            if (DateUtil.isIn(parent.getUpdateTime(),startDate,new Date())){
                totalCount.incrementAndGet();
                ncovAreaInfoService.insert(parent);
                List<NcovAreaInfo> cities = parent.getCities();
                if (CollUtil.isNotEmpty(cities)){
                    for (NcovAreaInfo children:cities){
                        totalCount.incrementAndGet();
                        BeanUtils.copyProperties(parent,children,"id","deadCount","curedCount","suspectedCount","confirmedCount","currentConfirmedCount","locationId","cities","cityEnglishName","cityName");
                        children.setParentAreaId(parent.getLocationId());
//                        ncovAreaInfoService.insert(children);
                        log.info("城市数据:"+children);
                    }
                }
                if (parent.getProvinceName().contains("钻石")){
                    boatCount.incrementAndGet();
                }
                if (!parent.getCountryName().contains("中国")){
                    boardCount.incrementAndGet();
                    countrySet.add(parent.getCountryName()+":"+parent.getProvinceName());
                }
            }
        }
        Integer dataCount = ncovAreaInfoService.countData();
        log.info("国内数据量:{},海外数据量{}",domesticCount,boardCount);
        log.info("起始采集数据量:{}条,清除今日数据:{}条，新增数据总量:{},采集后数据总量:{}",startCount,deleteCount,totalCount,dataCount);
        log.info("数据处理耗时:{}",(System.currentTimeMillis()-startTime));
    }

    @Test
    public void getRumors(){
        List<RumorInfo> all = rumorInfoService.findAll();
        Map<String ,RumorInfo> rumorMap=new HashMap<>();
        all.forEach(item->{
            rumorMap.put(item.get_id(),item);
        });
        int rumorCount=0;
        int reliableCount=0;
        int unConfirmCount=0;
        int newCount=0;
        for (int i=0;i<3;i++){
            String url=String.format(RUMORS_INFO_URL,i);
            ResponseEntity<JSONObject> forEntity = restTemplate.getForEntity(url, JSONObject.class);
            JSONArray results = forEntity.getBody().getJSONArray("results");
            for (Object result:results){
                JSONObject jsonObject=(JSONObject) result;
                RumorInfo rumorInfo = jsonObject.toBean(RumorInfo.class);
                if (!rumorMap.containsKey(rumorInfo.get_id())){
                    newCount++;
                    rumorInfoService.insert(rumorInfo);
                }
            }
            if (RumorType.RUMOR.equals(String.valueOf(i))){
                rumorCount=results.size();
                log.info("谣言信息量:"+results.size());
            }
            if (RumorType.RELIABLE.equals(String.valueOf(i))){
                reliableCount=results.size();
                log.info("可信信息量:"+results.size());
            }
            if (RumorType.UN_CONFIRMED.equals(String.valueOf(i))){
                unConfirmCount=results.size();
                log.info("未证实信息量:"+results.size());
            }
        }
        List<RumorInfo> resultTotal = rumorInfoService.findAll();
        int total=rumorCount+reliableCount+unConfirmCount;
        log.info("本次采集谣言信息量:{},可靠信息量:{},未证实信息量:{}",rumorCount,reliableCount,unConfirmCount);
        log.info("共获取数据量:{}条,入库前数量:{},本次新增:{},入库后数量:{}",total,all.size(),newCount,resultTotal.size());
    }
}
