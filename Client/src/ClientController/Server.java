package ClientController;

import Model.Request.Account.*;
import Model.Request.*;
import Model.Request.Chats.DownloadFileRequest;
import Model.Request.Chats.GetPersonPrivateChatsRequest;
import Model.Request.Chats.SendMessagePrivateChatRequest;
import Model.Request.Chats.UploadFileRequest;
import Model.Request.Friend.*;
import Model.Request.SC.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
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
            socket = new Socket("localhost", 11223);
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
     * @param bytes    file bytes
     * @param format   file format (suffix)
     * @param userName person userName
     */
    public void setPersonProfilePicture(String userName, byte[] bytes, String format) {
        ChangeProfilePictureRequest request = new ChangeProfilePictureRequest(userName, bytes, format);
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
    public HashSet<String> getServerChannels(Integer uniqueID) {
        GetServerChannelsRequest request = new GetServerChannelsRequest(uniqueID);
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
     * @param serverID serverID
     */
    public void removeServer(Integer serverID)
    {
        RemoveServerRequest request= new RemoveServerRequest(serverID);
        sendRequest(request);
    }

    /**
     * adds a person to server
     * @param userName person username
     * @param serverUniqueID server id
     */
    public void addPersonToServer(String userName,Integer serverUniqueID)
    {
        AddPersonToServerRequest request = new AddPersonToServerRequest(userName,serverUniqueID);
        sendRequest(request);
    }
    /**
     * removes a person from server
     * @param userName person username
     * @param serverUniqueID server id
     */
    public void removePersonFromServer(String userName, Integer serverUniqueID)
    {
        RemovePersonFromServerRequest request = new RemovePersonFromServerRequest(userName,serverUniqueID);
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
     * @param personUserName person username
     * @param serverID server id
     * @return result
     */
    public boolean isPersonExistInServer(String personUserName,Integer serverID)
    {
        CheckIsPersonExistInServerRequest request = new CheckIsPersonExistInServerRequest(personUserName, serverID);
        return ((CheckIsPersonExistInServerRequest)sendRequest(request)).isExist();
    }
    /**
     * changes user password
     * @param username person username
     * @param currentPass current password
     * @param newPass new password
     * @return chnging result
     */
    public boolean changePersonPassword(String username,String currentPass,String newPass)
    {
        ChangePasswordRequest request = new ChangePasswordRequest(username,currentPass,newPass);
        return ((ChangePasswordRequest)sendRequest(request)).isResult();
    }
    /**
     * upload a file in server
     * @param bytes file bytes
     * @return
     */
    public Integer uploadFile(byte[] bytes,String extension) {
        UploadFileRequest request = new UploadFileRequest(new ChatFile(bytes,extension));
        return ((UploadFileRequest)sendRequest(request)).getFileID();
    }
    /**
     * downloads a file from server
     * @param id file id
     * @return ChatFile
     */
    public ChatFile downloadFile(Integer id) {
        DownloadFileRequest request = new DownloadFileRequest(id);
        return ((DownloadFileRequest)sendRequest(request)).getFile();
    }
}
