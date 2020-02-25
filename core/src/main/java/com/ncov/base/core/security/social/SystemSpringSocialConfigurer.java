package com.ncov.base.core.security.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * <pre>
 *    描述：配置social的过滤器
 * </pre>
 * @author ChenJunLin
 * @version v1.0
 * @date 2020/2/18 15:57
 */
public class SystemSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    public SystemSpringSocialConfigurer(String filterProcessesUrl){
        this.filterProcessesUrl=filterProcessesUrl;
    }

    /**
     * <pre>
     *      功能描述: 在SpringSocialConfigurer的configure
     *         将SocialAuthenticationFilter配置进去之前
     *         会调用postProcess方法，利用这个方法对过滤器做一些配置
     *         1.在这里配置的链接是修改表单上获取授权的链接
     *         2.默认“/auth”+ providerId，这里的providerId可以
     *         通过创建connectionFactory的时候进行配置
     * </pre>
     * @author ChenJunLin
     * @param object
     * @return T
     * @date 2020/2/18 16:21
     */
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        //配置认证服务器的请求链接和回调链接，都是同一条
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }
}
