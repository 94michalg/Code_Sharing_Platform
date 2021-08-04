<html>
<head>
  <title>Latest</title>
  <link rel="stylesheet"
         href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
  <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
  <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<#list arrayList>
    <#items as pieceOfCode>
     	<span id="load_date">${pieceOfCode.date}</span><br>
  	<pre id="code_snippet"><code>${pieceOfCode.code}</code></pre>
	<#sep><br><br></#sep>
    </#items>
</#list>

</body>
</html>