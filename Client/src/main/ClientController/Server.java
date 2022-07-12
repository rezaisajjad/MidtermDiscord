package ClientController;

import code.Account.*;
import code.*;
import code.Chats.DownloadFileRequest;
import code.Chats.GetPersonPrivateChatsRequest;
import code.Chats.SendMessagePrivateChatRequest;
import code.Chats.UploadFileRequest;
import code.Friend.*;
import code.SC.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Server {
    private static Server server = new Server();

    private Server() {
    }

    public static Server getServer() {
        return server;
    }

    private IRequest sendRequest(IRequest request) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", Statics.MainPort);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            return (IRequest) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * sign up a new account in server
     *
     * @param p Model.Request.Person
     * @return signed up person
     */
    public Person signUpPerson(Person p) {
        SignUpRequest request = new SignUpRequest(p);
        return ((SignUpRequest) sendRequest(request)).getP();
    }

    /**
     * login an account
     *
     * @param userName UserName
     * @param passWord PassWord
     * @return logged in person
     */
    public Person loginPerson(String userName, String passWord) {
        LoginRequest request = new LoginRequest(userName, passWord);
        return ((LoginRequest) sendRequest(request)).getP();
    }

    /**
     * check if a username is available
     *
     * @param userName username
     * @return true:available false:unavailable
     */
    public boolean checkUserNameAvailability(String userName) {
        CheckUserNameAvailabilityRequest request = new CheckUserNameAvailabilityRequest(userName);
        return ((CheckUserNameAvailabilityRequest) sendRequest(request)).isAvailable();
    }

    /**
     * return private chats of a person
     *
     * @param userName userName
     * @return list of chats
     */
    public ArrayList<PrivateChat> getPersonPrivateChats(String userName) {
        GetPersonPrivateChatsRequest request = new GetPersonPrivateChatsRequest(userName);
        return ((GetPersonPrivateChatsRequest) sendRequest(request)).getPrivateChats();
    }

    /**
     * send a message to private chat
     *
     * @param pc  private chat
     * @param pcm message
     */
    public void sendPrivateChatMessage(PrivateChat pc, PrivateChatMessage pcm) {
        SendMessagePrivateChatRequest request = new SendMessagePrivateChatRequest(pc, pcm);
        sendRequest(request);
    }

    /**
     * the friends of a person
     *
     * @param userName Username
     * @return friendList
     */
    public ArrayList<String> getPersonFriends(String userName) {
        GetAcceptedFriendsRequest request = new GetAcceptedFriendsRequest(userName);
        return ((GetAcceptedFriendsRequest) sendRequest(request)).getFriends();
    }

    /**
     * sends a friend request
     *
     * @param sender   friend request sender
     * @param receiver friend request receiver
     */
    public void sendFriendRequest(String sender, String receiver) {
        AddFriendRequest request = new AddFriendRequest(sender, receiver);
        sendRequest(request);
    }

    /**
     * removes a friend
     *
     * @param remover  friend remover
     * @param receiver removed friend
     */
    public void removePersonFriend(String remover, String receiver) {
        RemoveFriendRequest request = new RemoveFriendRequest(remover, receiver);
        sendRequest(request);
    }

    /**
     * returns list of requests
     *
     * @param userName who you want to get requests
     * @return list of request
     */
    public ArrayList<AddFriendRequest> getPersonFriendRequests(String userName) {
        GetPersonRequestsRequest request = new GetPersonRequestsRequest(userName);
        return ((GetPersonRequestsRequest) sendRequest(request)).getRequests();
    }

    /**
     * accept a friend request
     *
     * @param friendRequest request
     */
    public void acceptFriendRequest(AddFriendRequest friendRequest) {
        AcceptFriendRequest acceptFriendRequest = new AcceptFriendRequest(friendRequest);
        sendRequest(acceptFriendRequest);
    }

    /**
     * blocks a person
     *
     * @param blocker person who block
     * @param blocked blocked person
     */
    public void blockPerson(String blocker, String blocked) {
        BlockPersonRequest request = new BlockPersonRequest(blocker, blocked);
        sendRequest(request);
    }

    /**
     * unblocks a person
     *
     * @param blocker person who block
     * @param blocked blocked person
     */
    public void unBlockPerson(String blocker, String blocked) {
        UnBlockPersonRequest request = new UnBlockPersonRequest(blocker, blocked);
        sendRequest(request);
    }

    /**
     * returns blocked persons
     *
     * @param userName person who block
     * @return block list
     */
    public ArrayList<String> getPersonBlockList(String userName) {
        GetBlockListRequest request = new GetBlockListRequest(userName);
        return ((GetBlockListRequest) sendRequest(request)).getPersons();
    }

    /**
     * return person status
     *
     * @return person status
     */
    public String getStatus(String userName) {
        GetStatusRequest request = new GetStatusRequest(userName);
        return ((GetStatusRequest) sendRequest(request)).getStatus();
    }

    /**
     * sets person status
     *
     */
    public void setStatus(String userName, Status status) {
        ChangeStatusRequest request = new ChangeStatusRequest(userName,status);
        sendRequest(request);
    }

    /**
     * change profile picture
     *
     * @param fileID    fileID
     * @param userName person userName
     */
    public void setPersonProfilePicture(String userName, String fileID) {
        ChangeProfilePictureRequest request = new ChangeProfilePictureRequest(userName, fileID);
        sendRequest(request);
    }

    /**
     * creat a new serverChat
     *
     * @param creator    creator username
     * @param serverName name
     */
    public void createServer(String creator, String serverName) {
        CreateServerRequest request = new CreateServerRequest(creator, serverName);
        sendRequest(request);
    }

    /**
     * returns server list name and uniqueID
     *
     * @param userName person who want server list
     * @return server list name and uniqueID
     */
    public HashMap<Integer, String> getPersonServerChats(String userName) {
        GetPersonServersRequest request = new GetPersonServersRequest(userName);
        return ((GetPersonServersRequest) sendRequest(request)).getList();
    }

    /**
     * create a channel in specific serve
     *
     * @param name           channel name
     * @param type           channel type
     * @param serverUniqueID channel server
     */
    public void createServerChannel(String name, ChannelType type, Integer serverUniqueID) {
        CreateServerChannelRequest request = new CreateServerChannelRequest(name, type, serverUniqueID);
        sendRequest(request);
    }

    /**
     * check if a name is free
     *
     * @param serverUniqueID serverUniqueID
     * @param channelName    channelName
     * @return result
     */
    public boolean checkChannelNameAvailability(Integer serverUniqueID, String channelName) {
        CheckChannelNameAvailabilityRequest request = new CheckChannelNameAvailabilityRequest(serverUniqueID, channelName);
        return ((CheckChannelNameAvailabilityRequest) sendRequest(request)).getResult();
    }

    /**
     * return all channels name of a server
     *
     * @param uniqueID serverID
     * @return list of names
     */
    public HashSet<String> getServerChannels(Integer uniqueID,String userName) {
        GetServerChannelsRequest request = new GetServerChannelsRequest(uniqueID,userName);
        return ((GetServerChannelsRequest) sendRequest(request)).getChannelsName();
    }

    /**
     * returns person roles
     *
     * @param userName person username
     * @param serverUniqueID serverID
     * @return list of rules
     */
    public ArrayList<Role> getPersonRoles(String userName, Integer serverUniqueID) {
        GetPersonRolesRequest request= new GetPersonRolesRequest(serverUniqueID,userName);
        return ((GetPersonRolesRequest)sendRequest(request)).getRoles();
    }

    /**
     * removes server
     *
     * @param serverID serverID
     */
    public void removeServer(Integer serverID) {
        RemoveServerRequest request = new RemoveServerRequest(serverID);
        sendRequest(request);
    }

    /**
     * adds a person to server
     *
     * @param userName       person username
     * @param serverUniqueID server id
     */
    public void addPersonToServer(String userName, Integer serverUniqueID) {
        AddPersonToServerRequest request = new AddPersonToServerRequest(userName, serverUniqueID);
        sendRequest(request);
    }

    /**
     * removes a person from server
     *
     * @param userName       person username
     * @param serverUniqueID server id
     */
    public void removePersonFromServer(String userName, Integer serverUniqueID) {
        RemovePersonFromServerRequest request = new RemovePersonFromServerRequest(userName, serverUniqueID);
        sendRequest(request);
    }

    /**
     * returns list of server members
     *
     * @param serverUniqueID server id
     * @return Hashset <#UserName, #Status>
     */
    public HashMap<String, String> getServerMembers(Integer serverUniqueID) {
        GetMemberServerListRequest request = new GetMemberServerListRequest(serverUniqueID);
        return ((GetMemberServerListRequest) sendRequest(request)).getUsers();
    }

    /**
     * adds a role to server
     *
     * @param role     role
     * @param uniqueID server id
     */
    public void addRoleToServer(Role role, Integer uniqueID) {
        AddRoleToServerRequest request = new AddRoleToServerRequest(role, uniqueID);
        sendRequest(request);
    }

    /**
     * return all roles
     *
     * @param serverID serverID
     * @return list of roles name
     */
    public HashSet<String> getServerRoles(Integer serverID) {
        GetServerRolesRequest request = new GetServerRolesRequest(serverID);
        return ((GetServerRolesRequest) sendRequest(request)).getRoles();
    }

    /**
     * changes server name
     *
     * @param name           new name
     * @param serverUniqueID server id
     */
    public void changeServerName(String name, Integer serverUniqueID) {
        ChangeServerNameRequest request = new ChangeServerNameRequest(serverUniqueID, name);
        sendRequest(request);
    }

    /**
     * checks if a person exist in server or no
     *
     * @param personUserName person username
     * @param serverID       server id
     * @return result
     */
    public boolean isPersonExistInServer(String personUserName, Integer serverID) {
        CheckIsPersonExistInServerRequest request = new CheckIsPersonExistInServerRequest(personUserName, serverID);
        return ((CheckIsPersonExistInServerRequest) sendRequest(request)).isExist();
    }

    /**
     * changes user password
     *
     * @param username    person username
     * @param currentPass current password
     * @param newPass     new password
     * @return changing result
     */
    public boolean changePersonPassword(String username, String currentPass, String newPass) {
        ChangePasswordRequest request = new ChangePasswordRequest(username, currentPass, newPass);
        return ((ChangePasswordRequest) sendRequest(request)).isResult();
    }

    /**
     * upload a file in server
     *
     * @param bytes file bytes
     * @return file id
     */
    public Integer uploadFile(byte[] bytes, String extension) {
        UploadFileRequest request = new UploadFileRequest(new ChatFile(bytes, extension));
        return ((UploadFileRequest) sendRequest(request)).getFileID();
    }

    /**
     * downloads a file from server
     *
     * @param id file id
     * @return ChatFile
     */
    public ChatFile downloadFile(Integer id) {
        DownloadFileRequest request = new DownloadFileRequest(id);
        return ((DownloadFileRequest) sendRequest(request)).getFile();
    }

    /**
     * removes a role from person
     *
     * @param userName       person username
     * @param roleName       name of role
     * @param serverUniqueID server id
     */
    public void removeRoleFromPerson(String userName, String roleName, Integer serverUniqueID) {
        RemoveRoleFromPersonServerRequest request = new RemoveRoleFromPersonServerRequest(serverUniqueID, roleName, userName);
        sendRequest(request);
    }

    /**
     * adds a role to person
     *
     * @param userName       person username
     * @param roleName       name of role
     * @param serverUniqueID server id
     */
    public void addRoleToPerson(String userName, String roleName, Integer serverUniqueID) {
        AddRoleToPersonServerRequest request = new AddRoleToPersonServerRequest(serverUniqueID, roleName, userName);
        sendRequest(request);
    }

    /**
     * returns member list of a server role
     *
     * @param roleName       role name
     * @param serverUniqueID server id
     * @return HashMap<# username, # status }>
     */
    public HashMap<String, String> getServerRoleMembers(String roleName, Integer serverUniqueID) {
        GetRoleMembersRequest request = new GetRoleMembersRequest(serverUniqueID, roleName);
        return ((GetRoleMembersRequest) sendRequest(request)).getUsers();
    }

    /**
     * restrict person from whole server
     *
     * @param userName       person userName
     * @param serverUniqueID server id
     */
    public void restrictPersonFromAllServer(String userName, Integer serverUniqueID) {
        RestrictPersonFromAllServerRequest request = new RestrictPersonFromAllServerRequest(serverUniqueID, userName);
        sendRequest(request);
    }

    /**
     * restrict person from a channel
     *
     * @param userName       person userName
     * @param serverUniqueID server id
     */
    public void restrictPersonFromAChannel(String userName, Integer serverUniqueID, String channelName) {
        RestrictPersonFromAChannelRequest request = new RestrictPersonFromAChannelRequest(serverUniqueID, userName, channelName);
        sendRequest(request);
    }

    /**
     * remove restrict person from whole server
     *
     * @param userName       person userName
     * @param serverUniqueID server id
     */
    public void removeRestrictPersonFromAllServer(String userName, Integer serverUniqueID) {
        RemoveRestrictPersonFromAllServerRequest request = new RemoveRestrictPersonFromAllServerRequest(serverUniqueID, userName);
        sendRequest(request);
    }

    /**
     * remove restrict person from a channel
     *
     * @param userName       person userName
     * @param serverUniqueID server id
     */
    public void removeRestrictPersonFromAChannel(String userName, Integer serverUniqueID, String channelName) {
        RemoveRestrictPersonFromAChannelRequest request = new RemoveRestrictPersonFromAChannelRequest(serverUniqueID, userName, channelName);
        sendRequest(request);
    }

    /**
     * returns list of person id
     *
     * @param serverUniqueID server id
     * @param channelName    channel name
     * @return list of person id
     */
    public HashSet<String> getRestrictPersonsFromAChannel(Integer serverUniqueID, String channelName) {
        GetRestrictPersonsFromAChannelRequest request = new GetRestrictPersonsFromAChannelRequest(serverUniqueID, channelName);
        return ((GetRestrictPersonsFromAChannelRequest) sendRequest(request)).getList();
    }

    /**
     * returns whole restrict persons
     *
     * @param serverUniqueID server id
     * @return list of person id
     */
    public HashSet<String> getRestrictPersonsFromAllServer(Integer serverUniqueID) {
        GetRestrictPersonsFromAllServerRequest request = new GetRestrictPersonsFromAllServerRequest(serverUniqueID);
        return ((GetRestrictPersonsFromAllServerRequest) sendRequest(request)).getList();
    }

    /**
     * unRestrict a person just from a channel
     * @param userName person username
     * @param serverUniqueID server id
     * @param channelName channelName
     */
    public void unRestrictAllRestrictPersonFromAChannel(String userName, Integer serverUniqueID, String channelName) {
        UnRestrictAllRestrictPersonFromAChannelRequest request = new UnRestrictAllRestrictPersonFromAChannelRequest(serverUniqueID, userName, channelName);
        sendRequest(request);
    }

    /**
     * returns a list of channels that person can see
     *
     * @param userName       person userName
     * @param serverUniqueID serverID
     * @return list of channels that person can see
     */
    public HashSet<String> getPersonFreemon(String userName, Integer serverUniqueID) {
        GetPersonFreedomRequest request = new GetPersonFreedomRequest(userName, serverUniqueID);
        return ((GetPersonFreedomRequest) sendRequest(request)).getChannels();
    }

    /**
     * return messages of a channel
     *
     * @param channelName channel name
     * @param serverID    server id
     * @param userName    person username
     * @return list of messages
     */
    public ArrayList<TextChannelMessage> getChannelMessages(String channelName, Integer serverID, String userName) {
        GetChannelMessagesRequest request = new GetChannelMessagesRequest(channelName, serverID, userName);
        return ((GetChannelMessagesRequest) sendRequest(request)).getMessages();
    }

    /**
     * pins a message to chat
     *
     * @param channelName channel name
     * @param serverID    server unique id
     * @param message     message
     */
    public void pinMessageToChannel(String channelName, Integer serverID, TextChannelMessage message) {
        PinMessageToChannelRequest request = new PinMessageToChannelRequest(message, channelName, serverID);
        sendRequest(request);
    }

    /**
     * return pins messages
     *
     * @param channelName channel name
     * @param serverID    server id
     * @return list of messages
     */
    public ArrayList<TextChannelMessage> getPinMessages(String channelName, Integer serverID) {
        GetPinedMessagesRequest request = new GetPinedMessagesRequest(channelName, serverID);
        return ((GetPinedMessagesRequest) sendRequest(request)).getMessages();
    }

    /**
     * add reaction to message
     *
     * @param channelName channelName
     * @param serverID    serverID
     * @param message     message
     * @param reaction    reaction
     */
    public void reactionToChannelMessage(String channelName, Integer serverID, TextChannelMessage message, Reaction reaction) {
        ReactionToChannelMessageRequest request = new ReactionToChannelMessageRequest(message, reaction, channelName, serverID);
        sendRequest(request);
    }

    /**
     * add message to channel
     *
     * @param channel  channelName
     * @param serverID serverID
     * @param text     message
     * @param sender   sender
     */
    public void sendChannelMessage(String channel, Integer serverID, String text, String sender) {
        SendChannelServerMessageRequest request = new SendChannelServerMessageRequest(text, sender, channel, serverID);
        sendRequest(request);
    }

    /**
     * returns new notifications
     * @param userName username
     * @return string notifications
     */
    public String getUpdates(String userName) {
        GetUpdatesRequest request= new GetUpdatesRequest(userName);
        return ((GetUpdatesRequest)sendRequest(request)).getUpdate();
    }

    /**
     * returns file of server Image
     * @param serverID server id
     * @return file
     */
    public Integer getServerImageID(Integer serverID) {
        GetServerImageRequest request=new GetServerImageRequest(serverID);
        return ((GetServerImageRequest)sendRequest(request)).getServerImage();
    }

    /**
     * sets image of a server
     *
     * @param serverID server id
     * @param fileID   fileId of image
     */
    public void setServerImage(Integer serverID, Integer fileID) {
        SetServerImageRequest request = new SetServerImageRequest(serverID, fileID);
        sendRequest(request);
    }

    /**
     * check if a email already exist or not
     *
     * @param email email
     * @return true:not exist --> available       false: exist --> unavailable
     */
    public boolean checkEmailAvailability(String email) {
        CheckEmailAvailabilityRequest request = new CheckEmailAvailabilityRequest(email);
        return ((CheckEmailAvailabilityRequest) sendRequest(request)).isAvailable();
    }

    /**
     * changes user password
     *
     * @param username person username
     * @param newMail  newEmail
     */
    public void changePersonEmail(String username, String newMail) {
        ChangeEmailRequest request = new ChangeEmailRequest(username, newMail);
        sendRequest(request);
    }
}
