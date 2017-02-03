import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;
import st.EntryMap;
import st.TemplateEngine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by nlfox on 2/2/17.
 */
@RunWith(Parameterized.class)
public class Task1 {
    EntryMap map;
    TemplateEngine engine;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Parameters(name = "{index}: {0},{1})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {true, "delete-unmatched"},
                {false, "delete-unmatched"},
                {true, "keep-unmatched"},
                {false, "keep-unmatched"}
        });
    }

    @Parameter // first data value (0) is default
    public /* NOT private */ boolean pCase;

    @Parameter(1)
    public /* NOT private */ String pMode;


    @Before
    public void setUp() throws Exception {
        map = new EntryMap();
        engine = new TemplateEngine();
    }


    //spec1 - Template cannot be NULL or empty. A runtime exception is thrown otherwise.
    @Test
    public void testEntryMapSpec1() {
        exception.expect(RuntimeException.class);
        map.store(null, "a", pCase);
    }

    //spec2 - Replace value string cannot be NULL. A runtime exception is thrown otherwise.
    @Test
    public void testEntryMapSpec2() {
        exception.expect(RuntimeException.class);
        map.store("test", null, pCase);
    }


    //spec3 - Case sensitive flag is optional and can be NULL. In the case of NULL case sensitive flag, template matching will be case insensitive.
    // TODO: How do we deal with the situation when the program throws Exception doesn't meet the spec?
    @Test
    public void testEntryMapSpec3() {
        map.store("name", "Adam", null);
        map.store("surname", "Dykes", null);
        map.store("age", "29", null);
        String result = engine.evaluate("Hello ${name}, is your age ${age ${symbol}}", map, "delete-unmatched");
        assertEquals("Hello Adam, is your age 29", result);
    }

    //    spec4 - The entries are ordered and follow the order in which they appear in the program.
    //    ---> In the previous example the "name"/"Adam" entry is the first entry since it was stored first.
    @Test
    public void testEntryMapSpec4() {
        map.store("name", "Aaa", false);
        map.store("name", "Adam", false);
        assertEquals(engine.evaluate("${name},${name}", map, pMode), "Aaa,Aaa");
    }


//    spec5 - Entries that already exist cannot be stored again.
//            ---> In the following commands only the first entry will be saved:
//            map.store("name", "Adam", false);
//                    map.store("name", "Adam", false);
//            ---> In the following commands, however, both entries will be saved:
//            map.store("name", "Adam", false);
//                    map.store("name", "Adam", true);

    @Test
    public void testEntryMapSpec5() {
        map.store("name", "Adam", false);
        map.store("name", "Adam", true);
        assertEquals(engine.evaluate("${Name},${name}", map, "delete-unmatched"), "Adam,Adam");
        map.store("name1", "Bob", false);
        map.store("name1", "Bob", false);
        assertEquals(engine.evaluate("${name1},${name1}", map, "delete-unmatched"), "Bob,Bob");
    }

    //spec1 - The template string can be NULL or empty. If template string NULL or empty, then the unchanged template string is returned.
    @Test
    public void testTemplateEngineSpec1() {
        assertEquals("", engine.evaluate("", map, pMode));
        assertEquals(null, engine.evaluate(null, map, pMode));

    }

    //spec2 - The EntryMap object can be NULL. If EntryMap object NULL, then the unchanged template string is returned.
    @Test
    public void testTemplateEngineSpec2() {
        assertEquals("", engine.evaluate("", null, pMode));
        assertEquals("test2333", engine.evaluate("test2333", map, pMode));

    }


}
