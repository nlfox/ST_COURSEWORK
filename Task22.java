import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import st.EntryMap;
import st.TemplateEngine;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by nlfox on 2/19/17.
 */
@RunWith(Parameterized.class)
public class Task22 {
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

    @Test //for m_0
    public void testTemplateEngineSpec6() {
        map.store("firstname", "Adam", pCase);
        map.store("prefix", "first", pCase);
        assertEquals("}Adam", engine.evaluate("}${${prefix}name}", map, pMode));
    }

    @Test //for m_0
    public void testTemplateEngineSpec7() {
        map.store("${aaa}", "test", pCase);
        /* Consider the situation, if we use ${aaa} as an entry and eval ${${aaa}}
        we have two template
        1. ${${aaa}}    -> ${aaa}
        2. ${aaa}       -> aaa
        if the template string is not order by the length, then in the delete-unmatched mode
        will not first delete the unmatched one while in the other the "${${aaa}}" will be
        replaced by "test"
        */

        if (pMode.equals("delete-unmatched")) {
            assertEquals("", engine.evaluate("${${aaa}}", map, pMode));
        } else {
            assertEquals("test", engine.evaluate("${${aaa}}", map, pMode));
        }

        // now we add "aaa", things changed, aaa get first evaluated.
        map.store("aaa", "aaa", pCase);

        if (pMode.equals("delete-unmatched")) {
            assertEquals("aaa", engine.evaluate("${${aaa}}", map, pMode));
        } else {
            assertEquals("aaa", engine.evaluate("${${aaa}}", map, pMode));
        }
    }

    @Test //for m_0
    public void additionalTest4() {
        assertEquals("test2333", engine.evaluate("test2333${}", map, "delete-unmatched"));
    }

    @Test //for m_0, m_3
    public void testTemplateEngineSpec8_basic() {
        map.store("a", "a", pCase);
        //Case 1: there is a match
        assertEquals("aaa", engine.evaluate("${a}${${a}}${${${a}}}", map, pMode));
    }

    @Test //for m_0
    public void jmak_identifyTemplates_3() {
        map.store("length10", "length11", pCase);
        map.store("length10}", "length104", pCase);
        map.store("{", "}", pCase);
        if (pMode.equals("keep-unmatched")) {
            assertEquals("length11${}", engine.evaluate("${length10}${}", map, pMode));
        } else {
            assertEquals("length11", engine.evaluate("${length10}${}", map, pMode));
        }

    }

    @Test
    public void testIdentifyTemplates_3() {
        map.store("x", "y", pCase);
        assertEquals("", engine.evaluate("", map, pMode));
    }

    @Test
    public void testIdentifyTemplates_1() {
        //1, 19
        map.store("x", "y", pCase);
        assertEquals("y", engine.evaluate("${x}", map, pMode));
    }

    @Test
    public void testIdentifyTemplates_2() {
        map.store("x", "y", pCase);
        map.store("{y", "z", pCase);
        map.store("y{", "rip", pCase);
        map.store("$a", "v", pCase);
        map.store("a$", "g", pCase);
        map.store("}a", "e", pCase);
        map.store("a}", "x", pCase);
        map.store("a", "b", pCase);
        assertEquals("$y", engine.evaluate("$${x}", map, pMode));
        assertEquals("$y$}", engine.evaluate("$${x}$}", map, pMode));
        assertEquals("v", engine.evaluate("${$a}", map, pMode));
        assertEquals("g", engine.evaluate("${a$}", map, pMode));

        if (pMode.equals("keep-unmatched")) {
            assertEquals("${}a}", engine.evaluate("${}a}", map, pMode));
        } else { //fails for m_2
            assertEquals("a}", engine.evaluate("${}a}", map, pMode));
        }
        assertEquals("b}", engine.evaluate("${a}}", map, pMode));
        assertEquals("rip", engine.evaluate("${y{}", map, pMode));
        assertEquals("{{z", engine.evaluate("{{${{y}", map, pMode));
        assertEquals("{$z", engine.evaluate("{$${{y}", map, pMode));
        assertEquals("{}z", engine.evaluate("{}${{y}", map, pMode));
        assertEquals("}$z", engine.evaluate("}$${{y}", map, pMode));
        assertEquals("}{z", engine.evaluate("}{${{y}", map, pMode));
    }

    @Test
    public void jmak_entrymap1() {
        map.store("AAaAbb", "aaAaBB", pCase);
        if (pCase == true) {
            assertEquals("aaAaBBaaAaBB", engine.evaluate("${AAaAbb}${AAaAbb}", map, pMode));

        } else {
            assertEquals("aaAaBBaaAaBB", engine.evaluate("${aaaabb}${AAAABB}", map, pMode));
        }
    }

    @Test
    public void jmak_entry() {
        map.store("AAaa", "bbbb", pCase);
        map.store("AaAa", "BBBB", !pCase);
        if (pCase == true) {
            map.store("AAaa", "bbbb", pCase);
            map.store("AaAa", "BBBB", !pCase);
            assertEquals("bbbbBBBB", engine.evaluate("${AAaa}${aaaa}", map, pMode));
        } else {
            map.store("AAaa", "bbbb", pCase);
            map.store("AaAa", "BBBB", !pCase);
            assertEquals("bbbbbbbb", engine.evaluate("${AAaa}${aaaa}", map, pMode));
        }
    }

    @Test
    public void jmak_identifyTemplates() { // for m_1
        map.store("a", "b", pCase);
        assertEquals("$a{a}", engine.evaluate("$a{a}", map, pMode));
    }


    @Test
    // kill m0 and m7
    public void m_0() {
        map.store("firstname", "Adam", pCase);
        map.store("prefix", "first", pCase);
        assertEquals("}Adam", engine.evaluate("}${${prefix}name}", map, pMode));
    }

    @Test
    // kill m_1
    public void m_1() {
        map.store("name", "Adam", pCase);
        assertEquals("$_{name}", engine.evaluate("$_{name}", map, pMode));
    }


    @Test
    // kill m_2
    public void m_2() {
        map.store("firstname", "Adam", pCase);
        assertEquals("$_{name}", engine.evaluate("$_{name}", map, pMode));
    }


    @Test
    // kill m_5
    public void m_5() {
        map.store("Name", "Adam", false);
        assertEquals("Adam", engine.evaluate("${name}", map, "keep-unmatched"));
    }

    @Test
    // kill m_6
    public void m_6() {
        map.store("name", "Adam", false);
        assertEquals("Adam", engine.evaluate("${Name}", map, "keep-unmatched"));
    }

    @Test
    // kill m_7 and 8
    public void m_7() {
        map.store("s1", "1", false);
        map.store("s2", "2", false);
        map.store("s3", "3", false);
        map.store("1_1_2", "test", false);
        assertEquals("test", engine.evaluate("${${s${s1}}_${s1}_${s2}}", map, "keep-unmatched"));
    }

    // kill m_9
    @Test
    public void m_9() {
        map.store("s1", "1", pCase);
        map.store("s2", "2", pCase);
        map.store("s3", "3", pCase);
        map.store("1__1_2", "test", pCase);
        map.store("s1111", "test", pCase);
        assertEquals("${test_${s111}_1_2}", engine.evaluate("${${s1111}_${s111}_${s1}_${s2}}", map, "keep-unmatched"));
    }


    @Test
    public void jmak_test1() {
        try {
            map.store(null, "a", pCase);
        } catch (RuntimeException e) {
            assertTrue(true);
            return;
        } catch (Exception e) {
            assertTrue(false);
            return;
        }
        assertTrue(false);
    }

    @Test
    public void jmak_test2() {
        try {
            map.store("", "a", pCase);
        } catch (RuntimeException e) {
            assertTrue(true);
            return;
        } catch (Exception e) {
            assertTrue(false);
            return;
        }
        assertTrue(false);
    }

    @Test
    public void jmak_test3() {
        try {
            map.store("a", null, pCase);
        } catch (RuntimeException e) {
            assertTrue(true);
            return;
        } catch (Exception e) {
            assertTrue(false);
            return;
        }
        assertTrue(false);
    }

    @Test
    public void jmak_test4() {
        try {
            map.store("a", "", pCase);
        } catch (RuntimeException e) {
            assertTrue(false);
            return;
        } catch (Exception e) {
            assertTrue(false);
            return;
        }
        assertTrue(true);
    }


}


