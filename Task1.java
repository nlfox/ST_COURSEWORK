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

import java.util.Arrays;
import java.util.Collection;

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
        assertEquals(engine.evaluate("${name1},${Name1}", map, "delete-unmatched"), "Bob,Bob");
    }

    //spec1 - The template string can be NULL or empty. If template string NULL or empty, then the unchanged template string is returned.
    @Test
    public void testTemplateEngineSpec1() {
        assertEquals("", engine.evaluate("", map, pMode));
        assertEquals(null, engine.evaluate(null, map, pMode));
        map.store("tets", "test", pCase);
        assertEquals("", engine.evaluate("", map, pMode));
        assertEquals(null, engine.evaluate(null, map, pMode));

    }

    //spec2 - The EntryMap object can be NULL. If EntryMap object NULL, then the unchanged template string is returned.
    @Test
    public void testTemplateEngineSpec2() {
        assertEquals("", engine.evaluate("", null, pMode));
        assertEquals("test2333${x}", engine.evaluate("test2333${x}", map, pMode));

    }

    //spec3 - Matching mode cannot be NULL and must be one of the possible values ("keep-unmatched" and "delete-unmatched").
    //If matching mode NULL or other value, it defaults to "delete-unmatched".
    @Test
    public void testTemplateEngineSpec3() {
        map.store("name", "Adam", pCase);
        assertEquals("Adam", engine.evaluate("${hello}${name}", map, null));
    }

    @Test
    public void testTemplateEngineSpec3_2() {
        map.store("name", "Adam", pCase);
        if (pCase == false) {
            assertEquals("Adam", engine.evaluate("${NaMe}", map, pMode));
        } else if (pMode.equals("keep-unmatched")) {
            assertEquals("${NaMe}", engine.evaluate("${NaMe}", map, pMode));
        } else {
            assertEquals("", engine.evaluate("${NaMe}", map, pMode));
        }
    }

    //spec4 - Templates in a template string occur between "${" and "}".
    // In a template, everything between its boundaries ("${" and "}") is treated as normal text when matched against an entry.
//            ---> In the template string "Hello ${name}, could you please give me your ${item} ?" the two templates are:
//            1 - ${name}
//            2 - ${item}
//            ---> The text of each template that will be matched against the EntryMap stored entries are:
//            1 - "name"
//            2 - "item"
//            (i.e. the template boundaries are omitted)
    @Test
    public void testTemplateEngineSpec4() {
        map.store("⁄€‹›ﬁﬂ‡°", "˝ÓÔÒ", pCase);
        assertEquals("${⁄€‹›ﬁﬂ‡°", engine.evaluate("${⁄€‹›ﬁﬂ‡°", map, pMode));
        assertEquals("⁄€‹›ﬁﬂ‡°}", engine.evaluate("⁄€‹›ﬁﬂ‡°}", map, pMode));
        assertEquals("˝ÓÔÒ", engine.evaluate("${⁄€‹›ﬁﬂ‡°}", map, pMode));

    }

    //spec5 - When a template is matched against an entry key, any non visible character does not affect the result.
//            ---> The entry "middle name"/"Peter" will match all of the following templates:
//            1 - ${middle name}
//                2 - ${middlename}
//                3 - ${middle       name}
    @Test
    public void testTemplateEngineSpec5() {
        map.store("my life", "x", pCase);
        assertEquals("x", engine.evaluate("${my\t \nlife}", map, pMode));
    }

    @Test
    public void testTemplateEngineSpec5_2() {
        map.store("my life", "x", pCase);
        assertEquals("x", engine.evaluate("${ my life }", map, pMode));
    }
    //spec6 - In a template string every "${" and "}" occurrence acts as a boundary of at MOST one template.
//            ---> Processing from left-to-right, each "}" occurrence that is not already a boundary to a template is matched to its closest preceding "${" occurrence which also is not already a boundary to a template.
//            ---> In the template string "I heard that }: ${name} said: ${we should try or best for winning the ${competition} cup.}" the templates are:
//            1 - ${name}
//                2 - ${competition}
//                3 - ${we should try or best for winning the ${competition} cup.}

    @Test
    public void testTemplateEngineSpec6() {
        map.store("firstname", "Adam", pCase);
        map.store("prefix", "first", pCase);
        assertEquals("}Adam", engine.evaluate("}${${prefix}name}", map, pMode));
    }

    //    spec7 - In a template string the different templates are ordered according to their length. The shorter templates precede.
//            ---> In the case of same-length templates, the one that occurs first when traversing the template string from left-to-right precedes.
//            ---> In the template string "abc}${de}${fgijk${lm}nopqr}${s}uvw${xyz" the sorted templates are:
//            1 - ${s}
//                2 - ${de}
//                3 - ${lm}
//                4 - ${fgijk${lm}nopqr}
    @Test
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

    //    spec8 - The engine processes one template at a time and attempts to match it against
    //              the keys of the EntryMap entries until there is a match or the entry list is exhausted.
//            ---> The engine processes both templates and entries according to their order.
//            ---> If there is a match:
//            1 - The template (including its boundaries) in the template string is replaced by the value of the matched entry.
//            2 - The same replace happens to all other templates which include the replaced template.
//            3 - The template engine moves on to the next template and repeats.
//            ---> If the entry list is exhausted and no match found for the current template:
//            1 - The template engine just moves on to the next template if matching the mode is "keep-unmatched".
//            2 - The engine deletes the unmatched template from the template string and all other templates which include it.
    @Test
    public void testTemplateEngineSpec8_basic() {
        map.store("a", "a", pCase);
        //Case 1: there is a match
        assertEquals("aaa", engine.evaluate("${a}${${a}}${${${a}}}", map, pMode));
    }

    @Test
    public void testTemplateEngineSpec8_exhausted() {
        //Case 2: the entry list is exhausted (ie. no match)
        map.store("a", "b", pCase);
        if (pMode.equals("delete-unmatched")) {
            assertEquals("", engine.evaluate("${x}", map, pMode));
        } else {
            assertEquals("${x}", engine.evaluate("${x}", map, pMode));
        }
    }

    @Test
    public void testTemplateEngineSpec8_step2() {

        map.store("name", "Adam", pCase);
        map.store("surname", "Dykes", pCase);
        map.store("age ${y}", "29", pCase);
        map.store("y", "24", pCase);
        //templates are sorted in the order:
        //1. ${y}
        //2. ${name}
        //3. ${symbol}
        //4. ${age 24 ${symbol}}
        //Evaluation order:
        //1. template1 (${y}) is matched against entry1 ("y"/"24") ===> match!
        //      --> Instanced string is now: "Hello ${name}24, is your age ${age 24 ${symbol}}"
        //2. template2 (${name}) is matched against entry1 ("name"/"Adam") ===> match!
        //      --> Instanced string is now: "Hello Adam24, is your age ${age 24 ${symbol}}"
        //3. template3 (${symbol}) is not matched, matching mode is "delete-unmatched"
        //   so template3 is deleted from the instanced string and all other templates containing it.
        //      --> Instanced string is now: "Hello Adam24, is your age ${age 24}"
        //      --> template4 is now ${age 24 }
        //4. template4 (${age 24 }) is not matched, matching mode is "delete-unmatched"
        //   so template3 is deleted from the instanced string and all other templates containing it.
        //      --> Instanced string is now: "Hello Adam24, is your age "
        assertEquals("Hello Adam24, is your age ", engine.evaluate("Hello Adam${y}, is your age ${age 24 ${symbol}}", map, "delete-unmatched"));
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
        assertEquals("test2333${x}", engine.evaluate("test2333${x}", map, "test"));
    }

}
