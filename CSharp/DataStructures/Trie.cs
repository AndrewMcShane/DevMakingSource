using System;
using System.Collections.Generic;

public class Trie
{
    public class TrieNode
    {
        public Dictionary<char, TrieNode> children;
        public bool isWord;

        public TrieNode()
        {
            children = new Dictionary<char, TrieNode>();
            isWord = false;
        }
    }

    private readonly TrieNode root;

    public Trie()
    {
        root = new TrieNode();
    }

    public void Put(string word)
    {
        TrieNode current = root;
        foreach(char child in word)
        {  
            if (!current.children.ContainsKey(child))
            {
                TrieNode tmp = new TrieNode();
                current.children.Add(child, tmp);
                
            }
            current = current.children[child];
        }
        current.isWord = true;
    }

    public bool Contains(string word)
    {
        TrieNode current = root;
        foreach(char child in word)
        {
            if (current.children.ContainsKey(child))
            {
                current = current.children[child];
            }
            else
            {
                return false;
            }
        }
        return current.isWord;
    }

    public void Remove(string word)
    {
        Remove(root, word, 0);
    }

    private bool Remove(TrieNode current, string word, int depth)
    {
        if(depth == word.Length)
        {
            current.isWord = false;
        }
        else
        {
            char child = word[depth];
            if (current.children.ContainsKey(child))
            {
                if(Remove(current.children[child], word, depth + 1) == false)
                {
                    current.children.Remove(child);
                }
            }
        }

        if(current.children.Count > 0)
        {
            return true;
        }
        return false;
    }

    public List<string> StartsWith(string prefix)
    {
        List<string> res = new List<string>();

        TrieNode current = root;
        foreach(char child in prefix)
        {
            // If exists: iterate down.
            if (current.children.ContainsKey(child))
            {
                current = current.children[child];
            }
            // Else, return empty list.
            else
            {
                return res;
            }
        }
        // Now at prefix, go down the sub-trees and return.
        StartsWith(current, prefix, res);
        return res;
    }

    private void StartsWith(TrieNode current, string prefix, List<string> words)
    {
        if (current.isWord)
        {
            words.Add(prefix);
        }

        // Iterate the keys
        foreach(char key in current.children.Keys)
        {
            StartsWith(current.children[key], prefix + key, words);
        }
    }
}