import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.Parameterized;
import st.EntryMap;
import st.TemplateEngine;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by nlfox on 2/19/17.
 */
public class Task2_part1 {
    EntryMap map;
    TemplateEngine engine;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Parameterized.Parameters(name = "{index}: {0},{1})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {true, "delete-unmatched"},
                {false, "delete-unmatched"},
                {true, "keep-unmatched"},
                {false, "keep-unmatched"}
        });
    }

    @Parameterized.Parameter // first data value (0) is default
    public /* NOT private */ boolean pCase;

    @Parameterized.Parameter(1)
    public /* NOT private */ String pMode;


    @Before
    public void setUp() throws Exception {
        map = new EntryMap();
        engine = new TemplateEngine();
    }


    @Test
    public void additionalTest1() {
        assertEquals("test2333${x}", engine.evaluate("test2333${x}", null, pMode));
    }

    @Test
    public void additionalTest2(){
        exception.expect(RuntimeException.class);
        map.store("","s",pCase);
        //assertEquals("test2333${x}", engine.evaluate("test2333${x}", map, pMode));
    }

    @Test
    public void additionalTest3(){
        assertEquals("test2333", engine.evaluate("test2333${x}", map, "test"));
    }

    @Test
    public void additionalTest4(){
        assertEquals("test2333", engine.evaluate("test2333${}", map, "delete-unmatched"));
    }

}
