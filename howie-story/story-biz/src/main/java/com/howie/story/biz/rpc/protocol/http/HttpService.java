package com.howie.story.biz.rpc.protocol.http;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

public class HttpService {

    public void start(String hostName,int port) {
        Tomcat tomcat =new Tomcat();
        Server server =tomcat.getServer();

        Service service = server.findService("Tomcat");
        Connector connector = new Connector();
        connector.setPort(port);

        Engine engine = new StandardEngine();
        engine.setDefaultHost(hostName);

        Host host = new StandardHost();
        host.setName(hostName);

        String contextPath = "";

        Context context = new StandardContext();
        context.setPath(contextPath);

        context.addLifecycleListener(new Tomcat.DefaultWebXmlListener());

        host.addChild(context);
        engine.addChild(host);
        service.setContainer(engine);
        service.addConnector(connector);


    }
}
