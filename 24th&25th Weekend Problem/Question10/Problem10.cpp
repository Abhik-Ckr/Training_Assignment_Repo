#include<bits/stdc++.h>

using namespace std;

class Solution {
public:
    string mostCommonWord(string paragraph, vector<string>& banned) {
        unordered_map<string, int> mpp;
        for(int i = 0; i < paragraph.length(); i++){
            if((paragraph[i] >= 'a' && paragraph[i] <= 'z') || (paragraph[i] >= 'A' && paragraph[i] <= 'Z')){
                paragraph[i] = tolower(paragraph[i]);
            }
            else{
                paragraph[i] = ' ';
            }
        }
        for(string ban : banned){
            mpp[ban] = -1;
        }
        stringstream ss(paragraph);
        string word;
        string ans;
        int count = 0;
        while(ss >> word){
            if(mpp.find(word) != mpp.end()){
                if(mpp[word] == -1){
                    continue;
                }
                else {
                    mpp[word]++;
                    if(count < mpp[word]){
                        count = mpp[word];
                        ans = word;
                    }
                }
            }
            else{
                mpp[word] = 1;
                if(count < 1){
                    count = 1;
                    ans = word;
                }
            }
        }
        return ans;
    }
};

int main() {
    string paragraph;
    int bannedCount;

    cout << "Enter the paragraph:\n";
    getline(cin, paragraph);

    cout << "Enter number of banned words: ";
    cin >> bannedCount;
    vector<string> banned(bannedCount);

    cout << "Enter banned words:\n";
    for (int i = 0; i < bannedCount; ++i) {
        cin >> banned[i];
    }

    Solution sol;
    string result = sol.mostCommonWord(paragraph, banned);

    cout << "Most Common Non-Banned Word: " << result << endl;

    return 0;
}
