package st;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

public class TemplateEngine
{
  private static final Character a = Character.valueOf('$');
  private static final Character b = Character.valueOf('{');
  private static final Character c = Character.valueOf('}');
  
  public String evaluate(String paramString1, EntryMap paramEntryMap, String paramString2)
  {
    Object localObject2 = paramEntryMap;
    if (!(localObject2 == null ? Boolean.FALSE : ((String)localObject1).isEmpty() ? Boolean.FALSE : (localObject1 = paramString1) == null ? Boolean.FALSE : Boolean.TRUE).booleanValue()) {
      return paramString1;
    }
    if ((localObject1 = paramString2) != null) {}
    if (!(((String)localObject1).equals("delete-unmatched") ? Boolean.TRUE : ((String)localObject1).equals("keep-unmatched") ? Boolean.TRUE : Boolean.FALSE).booleanValue()) {
      paramString2 = "delete-unmatched";
    }
    localObject2 = paramString1;
    Object localObject1 = this;
    HashSet localHashSet = new HashSet();
    Stack localStack = new Stack();
    Integer localInteger = Integer.valueOf(0);
    Object localObject3 = Boolean.FALSE;
    while (localInteger.intValue() < ((String)localObject2).length()) {
      if (Character.compare(((String)localObject2).charAt(localInteger.intValue()), a.charValue()) == 0)
      {
        localObject3 = Boolean.TRUE;
        localInteger = Integer.valueOf(localInteger.intValue() + 1);
      }
      else if (Character.compare(((String)localObject2).charAt(localInteger.intValue()), b.charValue()) == 0)
      {
        if (((Boolean)localObject3).booleanValue()) {
          localStack.add(localInteger);
        }
        localObject3 = Boolean.FALSE;
        localInteger = Integer.valueOf(localInteger.intValue() + 1);
      }
      else if (Character.compare(((String)localObject2).charAt(localInteger.intValue()), c.charValue()) == 0)
      {
        if (!localStack.isEmpty())
        {
          if ((localObject3 = (Integer)localStack.pop()).intValue() + 1 == localInteger.intValue()) {
            localObject3 = new c((TemplateEngine)localObject1, (Integer)localObject3, localInteger, "");
          } else {
            localObject3 = new c((TemplateEngine)localObject1, (Integer)localObject3, localInteger, ((String)localObject2).substring(((Integer)localObject3).intValue() + 1, localInteger.intValue()));
          }
          localHashSet.add(localObject3);
        }
        localObject3 = Boolean.FALSE;
        localInteger = Integer.valueOf(localInteger.intValue() + 1);
      }
      else
      {
        localObject3 = Boolean.FALSE;
        localInteger = Integer.valueOf(localInteger.intValue() + 1);
      }
    }
    localObject1 = a(localObject1 = localHashSet);
    return agetEntriesa;
  }
  
  private static ArrayList a(HashSet paramHashSet)
  {
    ArrayList localArrayList = new ArrayList();
    while (!paramHashSet.isEmpty())
    {
      Object localObject = null;
      Integer localInteger1 = Integer.valueOf(Integer.MAX_VALUE);
      Integer localInteger2 = Integer.valueOf(Integer.MAX_VALUE);
      Iterator localIterator = paramHashSet.iterator();
      while (localIterator.hasNext())
      {
        c localc1;
        c localc2;
        if (nextc.length() < localInteger1.intValue())
        {
          localObject = localc1;
          localInteger1 = Integer.valueOf(c.length());
          localInteger2 = a;
        }
        else if ((c.length() == localInteger1.intValue()) && (a.intValue() < localInteger2.intValue()))
        {
          localObject = localc1;
          localInteger1 = Integer.valueOf(c.length());
          localInteger2 = a;
        }
      }
      if (localObject != null)
      {
        paramHashSet.remove(localObject);
        localArrayList.add(localObject);
      }
      else
      {
        throw new RuntimeException();
      }
    }
    return localArrayList;
  }
  
  private b a(String paramString1, ArrayList paramArrayList1, ArrayList paramArrayList2, String paramString2)
  {
    Integer localInteger1 = Integer.valueOf(0);
    for (Integer localInteger2 = Integer.valueOf(0); localInteger2.intValue() < paramArrayList1.size(); localInteger2 = Integer.valueOf(localInteger2.intValue() + 1))
    {
      c localc = (c)paramArrayList1.get(localInteger2.intValue());
      Boolean localBoolean = Boolean.FALSE;
      for (Integer localInteger3 = Integer.valueOf(0); localInteger3.intValue() < paramArrayList2.size(); localInteger3 = Integer.valueOf(localInteger3.intValue() + 1))
      {
        a locala1 = (a)paramArrayList2.get(localInteger3.intValue());
        a locala2 = locala1;
        Object localObject1 = c.replaceAll("\\s", "");
        Object localObject2 = a.replaceAll("\\s", "");
        if ((c.booleanValue() ? Boolean.valueOf(((String)localObject1).equals(localObject2)) : Boolean.valueOf(((String)localObject1).toLowerCase().equals(((String)localObject2).toLowerCase()))).booleanValue())
        {
          paramString1 = a(paramString1, localc, localInteger2, b, paramArrayList1);
          localBoolean = Boolean.TRUE;
          break;
        }
      }
      if (localBoolean.booleanValue()) {
        localInteger1 = Integer.valueOf(localInteger1.intValue() + 1);
      } else if (paramString2.equals("delete-unmatched")) {
        paramString1 = a(paramString1, localc, localInteger2, "", paramArrayList1);
      } else {
        localInteger2 = Integer.valueOf(localInteger2.intValue() + 1);
      }
    }
    return new b(this, paramString1, localInteger1);
  }
  
  private static String a(String paramString1, c paramc, Integer paramInteger, String paramString2, ArrayList paramArrayList)
  {
    Object localObject2;
    Integer localInteger = Integer.valueOf(3 + c.length() - paramString2.length());
    Object localObject1;
    if (a.intValue() == 1) {
      localObject1 = "";
    } else {
      localObject1 = paramString1.substring(0, a.intValue() - 1);
    }
    if (b.intValue() == paramString1.length()) {
      paramString1 = "";
    } else {
      paramString1 = paramString1.substring(b.intValue() + 1);
    }
    (localObject2 = new StringBuilder()).append((String)localObject1);
    ((StringBuilder)localObject2).append(paramString2);
    ((StringBuilder)localObject2).append(paramString1);
    paramString1 = ((StringBuilder)localObject2).toString();
    for (paramString2 = paramInteger.intValue() + 1; paramString2 < paramArrayList.size(); paramString2++) {
      if ((geta.intValue() < a.intValue()) && (b.intValue() > b.intValue()))
      {
        paramInteger = Integer.valueOf(b.intValue() - localInteger.intValue());
        getb = paramInteger;
        localObject2 = paramString1.substring(geta.intValue() + 1, getb.intValue());
        getc = ((String)localObject2);
      }
      else if (a.intValue() > b.intValue())
      {
        localObject2 = Integer.valueOf(a.intValue() - localInteger.intValue());
        geta = ((Integer)localObject2);
        paramInteger = Integer.valueOf(b.intValue() - localInteger.intValue());
        getb = paramInteger;
      }
    }
    return paramString1;
  }
}

/* Location:
 * Qualified Name:     st.TemplateEngine
 * Java Class Version: 8 (52.0)
 * JD-Core Version:    0.7.1
 */