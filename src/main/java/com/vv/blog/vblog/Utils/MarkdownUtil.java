package com.vv.blog.vblog.Utils;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;

public class MarkdownUtil {
    public static String transfer(String content) {
        MutableDataSet mutableDataSet = new MutableDataSet();
        Parser parser = Parser.builder(mutableDataSet).build();
        HtmlRenderer htmlRenderer = HtmlRenderer.builder(mutableDataSet).build();
        Node node = parser.parse(content);
        return htmlRenderer.render(node);
    }
}
