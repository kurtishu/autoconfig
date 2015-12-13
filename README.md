<p>
    <span style="font-family: 微软雅黑, &#39;Microsoft YaHei&#39;;"><strong><span style="font-size: 18px;">Auto Config Tool</span></strong><br/></span>
</p>
<p>
    <font face="微软雅黑, Microsoft YaHei"><span style="font-size: 16px; font-family: 微软雅黑, &#39;Microsoft YaHei&#39;;">将配置文件转化成一个配置类， App在runtime的时候使用配置类。</span></font>
</p>
<p>
    <font face="微软雅黑, Microsoft YaHei"><span style="font-size: 16px; font-family: 微软雅黑, &#39;Microsoft YaHei&#39;;"><br/></span></font>
</p>
<p>
    <font face="微软雅黑, Microsoft YaHei"><span style="font-size: 16px; font-family: 微软雅黑, &#39;Microsoft YaHei&#39;;">比如，有配置文件 config.properties</span></font>
</p>

<pre class="brush:js;toolbar:false">
  CONFIG_1=Config1<br/>
  CONFIG_2=Config2
</pre>
<p>
    <span style="font-family: 微软雅黑, &#39;Microsoft YaHei&#39;;">程序编译以后会产生一个Config类</span>
</p>
<p>
    <br/>
</p>
<pre class="brush:js;toolbar:false">
public static final Config {  
    public static final CONFIG_1 = &quot;Config1&quot;;  <br/>
    public static final CONFIG_2 = &quot;Config2&quot;; <br/>
  }
</pre>
<p>
    <font face="微软雅黑, Microsoft YaHei"><br/></font>
</p>
<p>
    <font face="微软雅黑, Microsoft YaHei"><br/></font>
</p>
