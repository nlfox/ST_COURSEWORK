import org.junit.Before;
import st.EntryMap;
import st.TemplateEngine;

/**
 * Created by nlfox on 2/2/17.
 */
public class Task1 {
    EntryMap map;
    TemplateEngine engine;

    @Before
    public void setUp() throws Exception {
        map = new EntryMap();
        engine = new TemplateEngine();
    }


}
