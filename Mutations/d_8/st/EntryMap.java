package st;

import java.util.ArrayList;
import java.util.HashSet;

public class EntryMap
{
  private ArrayList a = new ArrayList();
  private HashSet b = new HashSet();
  
  public void store(String paramString1, String paramString2, Boolean paramBoolean)
  {
    if (paramBoolean == null) {
      paramBoolean = Boolean.FALSE;
    }
    if (!(b == null ? Boolean.FALSE : a.isEmpty() ? Boolean.FALSE : aa == null ? Boolean.FALSE : Boolean.TRUE).booleanValue()) {
      throw new RuntimeException();
    }
    paramBoolean = paramString1;
    paramString2 = this;
    if (Boolean.valueOf(!b.contains(paramBoolean)).booleanValue())
    {
      paramBoolean = paramString1;
      paramString2 = this;
      a.add(paramBoolean);
      b.add(paramBoolean);
    }
  }
  
  public ArrayList getEntries()
  {
    return a;
  }
}

/* Location:
 * Qualified Name:     st.EntryMap
 * Java Class Version: 8 (52.0)
 * JD-Core Version:    0.7.1
 */