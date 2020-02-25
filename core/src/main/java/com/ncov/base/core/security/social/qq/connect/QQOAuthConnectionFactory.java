package com.ncov.base.core.security.social.qq.connect;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;

public class QQOAuthConnectionFactory extends OAuth2ConnectionFactory {
    
    /**
     * <pre>
     *      功能描述: TODO
     * </pre>
     * @author ChenJunLin
     * @param providerId 服务提供商标识
     * @param appId 应用id
     * @param appSecret 应用密码
     * @return 
     * @date 2020/2/18 14:49
     */
    public QQOAuthConnectionFactory(String providerId,String appId,String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }

    @Override
    public Connection createConnection(AccessGrant accessGrant) {
        return super.createConnection(accessGrant);
    }
}
