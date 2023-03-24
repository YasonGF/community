package top.tswinter.nowcoder_community.util;

import org.apache.commons.lang3.CharUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class SensitiveFilter {
    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);

    private static final String REPLACEMENT = "***";

    private TrieNode root = new TrieNode();

    @PostConstruct
    public void init() {
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(in))
        ) {
            String keyword;
            while ((keyword = br.readLine()) != null) {
                this.addKeyWord(keyword);
            }

        } catch (IOException e) {
            logger.error("加载sensitive-words.txt文件失败：" + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private void addKeyWord(String keyword) {       // 将敏感词添加到前缀树中去
        TrieNode temp = root;

        for (int i = 0; i < keyword.length(); i++) {
            char c = keyword.charAt(i);
            TrieNode subNode = temp.getSubNode(c);
            if (subNode == null) {
                subNode = new TrieNode();
                temp.addSubNode(c, subNode);
            }
            temp = subNode;
            if (i == keyword.length() - 1) temp.isKeyWordEnd = true;
        }
    }

    public String filter(String text) {
        if (!StringUtils.hasText(text)) return null;

        TrieNode temp = root;
        int begin = 0, position = 0;
        StringBuilder sb = new StringBuilder();

        int len = text.length();
        while (position < len) {
            char c = text.charAt(position);
            if (isSymbol(c)) {
                if (temp == root) {
                    sb.append(c);
                    begin++;
                }
                position++;
                continue;
            }
            temp = temp.getSubNode(c);
            if (temp == null) {
                sb.append(text.charAt(begin));
                position = ++begin;
                temp = root;
            } else if (temp.isKeyWordEnd()) {
                sb.append(REPLACEMENT);
                begin = ++position;
                temp = root;
            }else {
                position++;
            }
        }
        sb.append(text.substring(begin));
        return sb.toString();
    }

    private boolean isSymbol(Character c) {
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2e80 || c > 0x9fff);
    }


    //    定义trie结构
    private class TrieNode {
        private boolean isKeyWordEnd = false;
        private Map<Character, TrieNode> subNodes = new HashMap<>();

        public boolean isKeyWordEnd() {
            return isKeyWordEnd;
        }

        public void setKeyWordEnd(boolean keyWordEnd) {
            isKeyWordEnd = keyWordEnd;
        }

        public Map<Character, TrieNode> getSubNodes() {
            return subNodes;
        }

        public void addSubNode(Character c, TrieNode node) {
            subNodes.put(c, node);
        }

        public TrieNode getSubNode(Character c) {
            return subNodes.get(c);
        }
    }
}
