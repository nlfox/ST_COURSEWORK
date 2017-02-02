import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import st.EntryMap;
import st.TemplateEngine;

/**
 * Created by nlfox on 2/2/17.
 */
public class Task1 {
    EntryMap map;
    TemplateEngine engine;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        map = new EntryMap();
        engine = new TemplateEngine();
    }

    @Test
    public void testEntryMapSpec1() {
        exception.expect(RuntimeException.class);
        map.store(null,"a",true);
        map.store(null,null,true);

    }


}
