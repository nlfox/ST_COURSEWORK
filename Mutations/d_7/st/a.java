package st;

final class a
{
  String a;
  String b;
  Boolean c;
  
  public a(EntryMap paramEntryMap, String paramString1, String paramString2, Boolean paramBoolean)
  {
    a = paramString1;
    b = paramString2;
    c = paramBoolean;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject == null) || (getClass() != paramObject.getClass())) {
      return false;
    }
    paramObject = (a)paramObject;
    Object localObject = this;
    if (!a.equals(a)) {
      return false;
    }
    localObject = this;
    if (!b.equals(b)) {
      return false;
    }
    localObject = this;
    if (c != null)
    {
      localObject = this;
      return c.equals(c);
    }
    return c == null;
  }
  
  public final int hashCode()
  {
    a locala1 = this;
    int i = a.hashCode();
    a locala2 = this;
    int j = i * 31 + b.hashCode();
    a locala3 = this;
    locala3 = this;
    int k;
    return k = j * 31 + (c != null ? c.hashCode() : 0);
  }
}

/* Location:
 * Qualified Name:     st.a
 * Java Class Version: 8 (52.0)
 * JD-Core Version:    0.7.1
 */