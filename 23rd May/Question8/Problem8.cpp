//Group Anagrams
// Given an array of strings, group the anagrams together.

#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        unordered_map<string, vector<string>> mpp;

        for(const string& str : strs){
            string sortedStr = str;
            sort(sortedStr.begin(), sortedStr.end());  // Sort to group anagrams
            mpp[sortedStr].push_back(str);
        }

        vector<vector<string>> result;
        for(auto& it : mpp){
            result.push_back(it.second);
        }

        return result;
    }
};

void printGroupedAnagrams(const vector<vector<string>>& groups) {
    for (const auto& group : groups) {
        cout << "[ ";
        for (const auto& word : group) {
            cout << "\"" << word << "\" ";
        }
        cout << "]" << endl;
    }
}

int main() {
    vector<string> input = {"eat", "tea", "tan", "ate", "nat", "bat"};

    Solution sol;
    vector<vector<string>> result = sol.groupAnagrams(input);

    cout << "Grouped Anagrams:" << endl;
    printGroupedAnagrams(result);

    return 0;
}
