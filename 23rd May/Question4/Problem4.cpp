//Add Two Numbers
//You are given two non-empty linked lists representing two non-negative integers. 
// Add the two numbers and return the sum as a linked list.
#include <bits/stdc++.h>
using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
};
ListNode* addTwoNumbers(ListNode* l1, ListNode* l2){
        ListNode *dummy = new ListNode(-1);
        ListNode *curr = dummy;
        ListNode *temp1 = l1;
        ListNode *temp2 = l2;
        int carry = 0;
        while(temp1 != NULL || temp2 != NULL){
            int sum = carry;
            
            if(temp1 != NULL){
                sum = sum + temp1->val;
            }
            if(temp2 != NULL){
                sum = sum + temp2->val;
            }
            ListNode* newNode = new ListNode(sum % 10);
            carry = sum / 10;
            curr -> next = newNode;
            curr = curr->next;
            if(temp1 != NULL){
                temp1 = temp1->next;
            }
            if(temp2 != NULL){
                temp2 = temp2->next;
            }
        }
        if(carry){
            ListNode* newNode = new ListNode(carry);
            curr -> next = newNode;
        }
        return dummy->next;
    }


ListNode* createList(int arr[], int n) {
    if(n == 0) return nullptr;
    ListNode *head = new ListNode(arr[0]);
    ListNode *current = head;
    for(int i = 1; i < n; i++) {
        current->next = new ListNode(arr[i]);
        current = current->next;
    }
    return head;
}

void printList(ListNode* head){
    while(head != NULL) {
        cout << head->val;
        if(head->next != NULL) cout << " -> ";
        head = head->next;
    }
    cout << endl;
}

ListNode* reverseLinkedList(ListNode* head){
    ListNode* prev = nullptr;
    ListNode* curr = head;
    while (curr != nullptr) {
        ListNode* nextNode = curr->next;
        curr->next = prev;
        prev = curr;
        curr = nextNode;
    }
    return prev;
}


int main()
{
    int arr1[] = {9, 9, 9};
    int arr2[] = {9, 9, 9};
    
    ListNode *l1 = createList(arr1, 3);
    ListNode *l2 = createList(arr2, 3);
    
    cout << "List 1: ";
    printList(l1);
    cout << "List 2: ";
    printList(l2);

    ListNode *result = addTwoNumbers(l1, l2);
    result = reverseLinkedList(result);

    cout << "Sum: ";
    printList(result);
    
    return 0;
}