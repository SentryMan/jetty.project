package org.eclipse.jetty.http.spi;

import java.net.InetSocketAddress;
import javax.net.ssl.SSLParameters;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory.Server;

class JettyHttpsParameters extends HttpsParameters 
{
  final InetSocketAddress addr;
  final HttpsConfigurator cfg;
  SslContextFactory.Server factoryServer = new SslContextFactory.Server();

  JettyHttpsParameters(HttpsConfigurator cfg, InetSocketAddress addr) 
  {
    this.addr = addr;
    this.cfg = cfg;
    factoryServer.setSslContext(cfg.getSSLContext());
  }

  @Override
  public InetSocketAddress getClientAddress() 
  {
    return addr;
  }

  @Override
  public HttpsConfigurator getHttpsConfigurator() 
  {
    return cfg;
  }

  SSLParameters params;

  @Override
  public void setSSLParameters(SSLParameters p) 
  {
    params = p;
    factoryServer.setWantClientAuth(p.getWantClientAuth());
    factoryServer.setNeedClientAuth(p.getNeedClientAuth());
  }

  Server getSSLFactoryServer() 
  {
    return factoryServer;
  }
}
