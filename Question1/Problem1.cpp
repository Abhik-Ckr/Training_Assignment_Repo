//1.	Two Sum II - Input array is sorted
//Given a sorted array of integers, return the indices of the two numbers such that they add up to a specific target.

#include <bits/stdc++.h>
using namespace std;

vector<int> twoSum(vector<int>& nums, int target){
    int i = 0;
    int j = nums.size() - 1;
    while(i <= j){
        int sum = nums[i] + nums[j];
        if(sum == target){
            return {i, j};
        }else if(sum > target){
            j--;
        }
        else{
            i++;
        }
    }
    return {-1, -1};
}

int main() {
    vector<int> nums = {1, 1, 2, 2};
    int target = 4;

    vector<int> result = twoSum(nums, target);

    for (int i : result) {
        cout << i << " ";
    }
    cout << endl;

    return 0;
}
