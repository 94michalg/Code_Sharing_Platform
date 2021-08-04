<html>
<head>
  <title>Code</title>
  <link rel="stylesheet"
         href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
  <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
  <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
  <span id="load_date">${date}</span><br>
  <#if restrictedTime == true><span id="time_restriction">${time}</span><br></#if>
  <#if restrictedViews == true><span id="views_restriction">${views}</span><br></#if>
  <pre id="code_snippet"><code>${code}</code></pre>
</body>
</html>