// Remove Nth Node From End of List
// Given a linked list, remove the nth node from the end and return its head.

#include<bits/stdc++.h>
using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
public:
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        ListNode* fast = head;
        for(int i = 0; i < n; i++) {
            fast = fast->next;
        }
        if(fast == NULL) {
            ListNode* temp = head;
            head = head->next;
            delete temp;
            return head;
        }

        ListNode* slow = head;
        while(fast->next != NULL) {
            fast = fast->next;
            slow = slow->next;
        }

        ListNode* delNode = slow->next;
        slow->next = slow->next->next;
        delete delNode;

        return head;
    }
};

ListNode* createList(const vector<int>& vals) {
    if(vals.empty()) return nullptr;
    ListNode* head = new ListNode(vals[0]);
    ListNode* current = head;
    for(size_t i = 1; i < vals.size(); ++i){
        current->next = new ListNode(vals[i]);
        current = current->next;
    }
    return head;
}

void printList(ListNode* head) {
    while(head) {
        cout << head->val;
        if(head->next) cout << " -> ";
        head = head->next;
    }
    cout << " -> X" << endl;
}

int main() {
    vector<int> vals = {1, 2, 3, 4, 5};
    int n = 2; 

    ListNode* head = createList(vals);
    cout << "Original list:\n";
    printList(head);

    Solution sol;
    head = sol.removeNthFromEnd(head, n);

    cout << "After removing " << n << "th node from the end:\n";
    printList(head);

    return 0;
}
