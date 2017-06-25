package smb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractQP implements Iqp {
    private final static Logger logger = LoggerFactory.getLogger(AbstractQP.class);
    protected abstract void login();
    protected abstract void getIndex();
    protected abstract void doqp();
    
    @Override
    public void beginqp(){
        login();
        getIndex();
        doqp();
    }
    
}
