package platform.web.templates;

public class HTMLTemplates {

    public static final String POST_CODE = "<html> \n" +
            "\t<head> \n" +
            "\t\t<title>Create</title> \n" +
            "\t</head> \n" +
            "        <body> \n" +
            "<label for=\"code_snippet\">Code snippet:</label>" +
            "\t<textarea id=\"code_snippet\"></textarea><br><br>\n" +
            "<label for=\"time_restriction\">Time restriction:</label>" +
            "\t<input id=\"time_restriction\" type=\"text\"/><br><br>\n" +
            "<label for=\"views_restriction\">Views restriction:</label>" +
            "\t<input id=\"views_restriction\" type=\"text\"/><br><br>\n" +
            "\t<button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button> \n" +
            "\t<script>\n" +
            "\tfunction send() {\n" +
            "        let object = {\n" +
            "            \"code\": document.getElementById(\"code_snippet\").value,\n" +
            "            \"time\": document.getElementById(\"time_restriction\").value,\n" +
            "            \"views\": document.getElementById(\"views_restriction\").value\n" +
            "        };\n" +
            "\n" +
            "        let json = JSON.stringify(object);\n" +
            "\n" +
            "        let xhr = new XMLHttpRequest();\n" +
            "        xhr.open(\"POST\", '/api/code/new', false)\n" +
            "        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');\n" +
            "        xhr.send(json);\n" +
            "\n" +
            "        if (xhr.status == 200) {\n" +
            "          alert(\"Success! \\r\\n\" + xhr.responseText);\n" +
            "        } else {\n" +
            "          alert(\"Something went wrong!\");\n" +
            "        }\n" +
            "    }\n" +
            "\t</script>\n" +
            "\n" +
            "\n" +
            "\t</body> \n" +
            "</html>";
}
