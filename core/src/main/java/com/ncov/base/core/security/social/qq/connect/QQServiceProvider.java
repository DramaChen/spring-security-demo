package com.ncov.base.core.security.social.qq.connect;

import com.ncov.base.core.security.social.qq.api.QQ;
import com.ncov.base.core.security.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    //获取授权码链接
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    //申请令牌链接
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
        this.appId=appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }
}
