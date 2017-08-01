package com.gzw.config;

import com.gzw.interceptor.AccessTokenVerifyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by gujian on 2017/7/10.
 */
@Configuration
@PropertySource("classpath:/https.properties")
//@EnableConfigurationProperties(MyWebConfig.TomcatSslConnectorProperties.class)
public class MyWebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    AccessTokenVerifyInterceptor interceptor;

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                //添加需要验证登录用户操作权限的请求
                .addPathPatterns("/**")
                //排除不需要验证登录用户操作权限的请求
                .excludePathPatterns("/","/do_login","/do_register","/login");
    }

//    @Bean
//    public EmbeddedServletContainerFactory servletContainer(TomcatSslConnectorProperties properties) {
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
//        tomcat.addAdditionalTomcatConnectors(createSslConnector(properties));
//        return tomcat;
//    }
//
//    private Connector createSslConnector(TomcatSslConnectorProperties properties) {
//        Connector connector = new Connector();
//        properties.configureConnector(connector);
//        return connector;
//    }
//    //配置https
//    @ConfigurationProperties(prefix = "custom.tomcat.https")
//    @Data
//    public static class TomcatSslConnectorProperties {
//
//        private Integer port;
//        private Boolean ssl = true;
//        private Boolean secure = true;
//        private String scheme = "https";
//        private File keystore;
//        private String keystorePassword;
//        //这里为了节省空间，省略了getters和setters，读者在实践的时候要加上
//
//        public void configureConnector(Connector connector) {
//            if (port != null) {
//                connector.setPort(port);
//            }
//            if (secure != null) {
//                connector.setSecure(secure);
//            }
//            if (scheme != null) {
//                connector.setScheme(scheme);
//            }
//            if (ssl != null) {
//                connector.setProperty("SSLEnabled", ssl.toString());
//            }
//            if (keystore != null && keystore.exists()) {
//                connector.setProperty("keystoreFile", keystore.getAbsolutePath());
//                connector.setProperty("keystorePassword", keystorePassword);
//            }
//        }
//    }
//
//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint constraint = new SecurityConstraint();
//                constraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                constraint.addCollection(collection);
//                context.addConstraint(constraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(httpConnector());
//        return tomcat;
//    }
//
//    @Bean
//    public Connector httpConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        //Connector监听的http的端口号
//        connector.setPort(8080);
//        connector.setSecure(false);
//        //监听到http的端口号后转向到的https的端口号
//        connector.setRedirectPort(8089);
//        return connector;
//    }
}
