import com.linkedin.common.callback.Callback;
import com.linkedin.common.util.None;
import com.linkedin.d2.balancer.servers.ZKUriStoreFactory;
import com.linkedin.d2.balancer.servers.ZooKeeperAnnouncer;
import com.linkedin.d2.balancer.servers.ZooKeeperConnectionManager;
import com.linkedin.d2.balancer.servers.ZooKeeperServer;

import java.io.IOException;

/**
 * Created by mqiu on 8/23/17.
 */
public class cluster {

    public static void main(String[] args) throws IOException {
        ZooKeeperAnnouncer[] zkAnnouncers = new ZooKeeperAnnouncer[3];
        zkAnnouncers[0] = new ZooKeeperAnnouncer(new ZooKeeperServer());
        zkAnnouncers[0].setUri("http://localhost:7071");
        zkAnnouncers[0].setCluster("mingCluster");
        zkAnnouncers[0].setWeight(1.0);
        zkAnnouncers[1] = new ZooKeeperAnnouncer(new ZooKeeperServer());
        zkAnnouncers[1].setUri("http://localhost:7072");
        zkAnnouncers[1].setCluster("mingCluster");
        zkAnnouncers[1].setWeight(1.0);
        zkAnnouncers[2] = new ZooKeeperAnnouncer(new ZooKeeperServer());
        zkAnnouncers[2].setUri("http://localhost:7073");
        zkAnnouncers[2].setCluster("mingCluster");
        zkAnnouncers[2].setWeight(1.0);

        ZooKeeperConnectionManager zooKeeperConnectionManager = new ZooKeeperConnectionManager("localhost:2181", 5000/*ms*/, "/d2", new ZKUriStoreFactory(), zkAnnouncers);
        zooKeeperConnectionManager.start(new Callback<None>() {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onSuccess(None result) {

            }
        });
        System.out.println("press any key to shut down zookeeper announcers");
        System.in.read();
    }
}
