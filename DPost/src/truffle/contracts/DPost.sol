//SPDX-License-Identifier: MIT
pragma solidity ^0.8.7;

contract DPost{
   
   uint256 count = 0;

   struct Post{
       string text;
       uint256 time;
       address userId;
       uint256 likeCount;
       address[] likeBy;
   }

    Post[]  posts;
   
   event postCreated(string text, uint256 time, address userId);

    //Creating a new post
   function createPost(string memory _text) public {
       
       uint256 _time = block.timestamp;
       address _userId = msg.sender;

       Post memory myPost;
       myPost.text = _text;
       myPost.time = _time;
       myPost.userId = _userId;
       myPost.likeCount = 0;

        posts.push(myPost);
        count++;
        emit postCreated(_text,_time,_userId);
       
   }

   function getAllPost() view public returns(Post[] memory){
       return posts;
   }

    function getCount() view public returns(uint256){
        return count;
    }

    function likePost(uint256 index, address addr)  public returns(bool){

        bool found = false;

        for(uint256 i=0;i<posts[index].likeBy.length;i++){
            if(addr == posts[index].likeBy[i]){
                found = true;
                return false;
            }
        }

        if(found == false){
            posts[index].likeCount++;
            posts[index].likeBy.push(addr);
        }

        return true;
    }


}