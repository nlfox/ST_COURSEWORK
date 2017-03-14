import org.junit.Before;
import org.junit.Test;
import st.EntryMap;
import st.TemplateEngine;

import static org.junit.Assert.assertEquals;

/**
 * Created by nlfox on 3/14/17.
 */
public class Task31 {
    EntryMap map;
    TemplateEngine engine;

    @Before
    public void setUp() throws Exception {
        map = new EntryMap();
        engine = new TemplateEngine();
    }

    //test keep
    @Test
    public void test1() {
        map.store("s1", "test1", true);
        map.store("s2", "test2", true);
        map.store("test1_${s3}", "test11", true);
        map.store("test11", "test111", true);
        assertEquals("test111", engine.evaluate("${${${s1}_${s3}}}", map, "optimization"));
    }


    //test delete
    @Test
    public void test2() {
        map.store("s1", "test1", true);
        map.store("s2", "test2", true);
        map.store("test1_", "test11", true);
        map.store("test11", "test111", true);
        assertEquals("test111", engine.evaluate("${${${s1}_${s3}}}", map, "optimization"));
    }

    //test equal
    @Test
    public void test3() {
        map.store("s1", "test1", true);
        map.store("s2", "test2", true);
        map.store("test1_", "test11", true);
        map.store("test11", "test111", true);
        map.store("test1_${s3}", "test12", true);
        map.store("test12", "test121", true);
        assertEquals("test121", engine.evaluate("${${${s1}_${s3}}}", map, "optimization"));
    }
}
