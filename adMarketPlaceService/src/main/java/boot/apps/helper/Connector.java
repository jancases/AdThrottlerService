package boot.apps.helper;

import java.util.concurrent.TimeUnit;

public class Connector extends AbstractDelayedWrapper {

    public Connector(int throttle) {
        super(TimeUnit.MILLISECONDS);
        delay = 1000 / throttle;
    }

    @Override
    public long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public boolean send(Object obj) {
        System.out.println("Packet send...");
        return true;
    }
}
