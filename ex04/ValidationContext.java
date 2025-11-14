import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ValidationContext {
    public final Set<String> numerosPersistidos = ConcurrentHashMap.newKeySet();

    private final Deque<Runnable> rollbackStack = new ArrayDeque<>();

    public final Map<ValidatorId, List<ValidatorId>> skipOnFailure = new HashMap<>();

    public int failures = 0;

    public void pushRollback(Runnable r){ if (r!=null) rollbackStack.push(r); }

    public void rollbackAll(){
        while(!rollbackStack.isEmpty()){
            try { rollbackStack.pop().run(); } catch(Exception ignored){}
        }
    }
}
