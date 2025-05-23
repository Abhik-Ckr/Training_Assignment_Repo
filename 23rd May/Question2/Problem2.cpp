//Subarray Sum Equals K
//Given an array of integers and a target sum k, return the total number of continuous subarrays whose sum equals to k.

#include<bits/stdc++.h>
using namespace std;

int subarraySum(vector<int>& nums, int k) {
    unordered_map<int, int> mpp;
    mpp[0] = 1; 
    int count = 0; 
    int sum = 0;

    for (int num : nums) {
        sum += num;
        int remaining = sum - k;
        if (mpp.find(remaining) != mpp.end()) {
            count += mpp[remaining];
        }
        mpp[sum]++;
    }

    return count;
}


int main() {
    
    vector<int> nums = {1, -1, 4};
    int k = 4;
    
    int result = subarraySum(nums, k);
    cout<<"the total number of continuous subarrays whose sum equals to k is ";
    cout<<result;
    
    return 0;
}