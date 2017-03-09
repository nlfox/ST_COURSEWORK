package st;

final class c
{
  Integer a;
  Integer b;
  String c;
  
  c(TemplateEngine paramTemplateEngine, Integer paramInteger1, Integer paramInteger2, String paramString)
  {
    a = paramInteger1;
    b = paramInteger2;
    c = paramString;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject == null) || (getClass() != paramObject.getClass())) {
      return false;
    }
    paramObject = (c)paramObject;
    Object localObject = this;
    if (a != null)
    {
      localObject = this;
      if (a.equals(a)) {
        break label70;
      }
    }
    else
    {
      if (a == null) {
        break label70;
      }
    }
    return false;
    label70:
    localObject = this;
    if (b != null)
    {
      localObject = this;
      if (b.equals(b)) {
        break label111;
      }
    }
    else
    {
      if (b == null) {
        break label111;
      }
    }
    return false;
    label111:
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
    c localc1 = this;
    localc1 = this;
    int i = a != null ? a.hashCode() : 0;
    c localc2 = this;
    localc2 = this;
    int j = i * 31 + (b != null ? b.hashCode() : 0);
    c localc3 = this;
    localc3 = this;
    int k;
    return k = j * 31 + (c != null ? c.hashCode() : 0);
  }
}

/* Location:
 * Qualified Name:     st.c
 * Java Class Version: 8 (52.0)
 * JD-Core Version:    0.7.1
 */