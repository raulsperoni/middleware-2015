import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.*;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by raul on 15/09/15.
 */
public class FTPServer {

    public static void main(String[] args) throws FtpException {
        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        UserManager userManager = userManagerFactory.createUserManager();
        BaseUser user = new BaseUser();
        user.setName("username");
        user.setPassword("password");
        user.setHomeDirectory("/tmp");
        userManager.save(user);

        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setPort(2221);

        FtpServerFactory factory = new FtpServerFactory();
        factory.setUserManager(userManager);
        factory.addListener("default", listenerFactory.createListener());

        HashMap<String, Ftplet> m = new HashMap<String, Ftplet>();
        m.put("lala", new Ftplet() {
            public void init(FtpletContext ftpletContext) throws FtpException {

            }

            public void destroy() {

            }

            public FtpletResult beforeCommand(FtpSession ftpSession, FtpRequest ftpRequest) throws FtpException, IOException {
                return null;
            }

            public FtpletResult afterCommand(FtpSession ftpSession, FtpRequest ftpRequest, FtpReply ftpReply) throws FtpException, IOException {
                return null;
            }

            public FtpletResult onConnect(FtpSession ftpSession) throws FtpException, IOException {
                return null;
            }

            public FtpletResult onDisconnect(FtpSession ftpSession) throws FtpException, IOException {
                return null;
            }
        });
        factory.setFtplets(m);

        FtpServer server = factory.createServer();
        server.start();


    }
}