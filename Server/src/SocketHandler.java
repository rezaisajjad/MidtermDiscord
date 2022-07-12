import Repository.PeopleRepository;
import code.Account.*;
import code.Chats.DownloadFileRequest;
import code.Chats.GetPersonPrivateChatsRequest;
import code.Chats.SendMessagePrivateChatRequest;
import code.Chats.UploadFileRequest;
import code.Friend.*;
import code.IRequest;
import code.SC.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketHandler extends Thread {
    public static PeopleRepository people = PeopleRepository.getInstance();

    private final Socket socket;

    private SocketHandler(Socket socket) {
        this.socket = socket;
        start();
        System.out.println("Connected: \n\t\t" + socket.toString());
    }

    public static SocketHandler New(Socket socket) {
        return new SocketHandler(socket);
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object request = inputStream.readObject();
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(processRequest(request));
            socket.close();
            {
                try {
                    FileOutputStream outputStream1 = new FileOutputStream(Main.address);
                    ObjectOutputStream outputStream2 = new ObjectOutputStream(outputStream1);
                    outputStream2.writeObject(PeopleRepository.pr);
                    outputStream2.flush();
                    outputStream1.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private IRequest processRequest(Object request) {
        //region Account
        if (request instanceof CheckUserNameAvailabilityRequest res) {
            res.setAvailability(people.isUserNameAvailable(res.getUserName()));
            return res;
        } else if (request instanceof LoginRequest res) {
            res.setP(people.loginPerson(res.getUserName(), res.getPassWord()));
            return res;
        } else if (request instanceof SignUpRequest res) {
            if (!people.addPerson(res.getP())) {
                res.setP(null);
            }
            if (res.getP() != null)
                res.setP(res.getP().cloneWithoutList());
            return res;
        } else if (request instanceof GetPersonPrivateChatsRequest res) {
            res.setPrivateChats(people.getPersonPrivateChats(res.getUserName()));
            return res;
        } else if (request instanceof SendMessagePrivateChatRequest res) {
            people.sendPrivateChatMessage(res.getPrivateChat(), res.getMessage());
            return res;
        } else if (request instanceof GetStatusRequest res) {
            res.setStatus(people.getStatus(res.getUserName()));
            return res;
        } else if (request instanceof RemoveFriendRequest res) {
            people.removeFriend(res.getSenderUserName(), res.getReceiverUserName());
            return res;
        } else if (request instanceof GetPersonRequestsRequest res) {
            res.setRequests(people.getPersonFriendRequests(res.getUserName()));
            return res;
        } else if (request instanceof AddFriendRequest res) {
            people.addFriend(res.getSenderUserName(), res.getReceiverUserName());
            return res;
        } else if (request instanceof AcceptFriendRequest res) {
            people.acceptFriendRequest(res.getFriendRequest().getReceiverUserName(), res.getFriendRequest().getSenderUserName());
            return res;
        } else if (request instanceof GetAcceptedFriendsRequest res) {
            res.setFriends(people.getPersonFriends(res.getUserName()));
            return res;
        } else if (request instanceof GetBlockListRequest res) {
            res.setPersons(people.getPersonBlockedList(res.getUserName()));
            return res;
        } else if (request instanceof BlockPersonRequest res) {
            people.blockAPerson(res.getBlocker(),res.getBlocked());
            return res;
        } else if (request instanceof UnBlockPersonRequest res) {
            people.unBlockAPerson(res.getBlocker(),res.getBlocked());
            return res;
        }else if (request instanceof ChangeStatusRequest res) {
            people.setStatus(res.getUserName(),res.getStatus());
            return res;
        }else if (request instanceof ChangeProfilePictureRequest res) {
            people.setPersonProfilePicture(res.getUserName(), res.getImageID());
            return res;
        } else if (request instanceof CreateServerRequest res) {
            people.createServer(res.getCreatorUserName(), res.getName());
            return res;
        } else if (request instanceof GetPersonServersRequest res) {
            res.setList(people.getPersonServerChats(res.getUserName()));
            return res;
        } else if (request instanceof CreateServerChannelRequest res) {
            people.createServerChannel(res.getName(),res.getType(),res.getServerUniqueID());
            return res;
        } else if (request instanceof CheckChannelNameAvailabilityRequest res) {
            res.setResult(people.checkChannelNameAvailability(res.getServerUniqueID(),res.getChannelName()));
            return res;
        } else if (request instanceof GetServerChannelsRequest res) {
            res.setChannelsName(people.getServerChannels(res.getServerUniqueID(), res.getUserName()));
            return res;
        }  else if (request instanceof GetPersonRolesRequest res) {
            res.setRoles(people.getPersonRoles(res.getUserName(),res.getUniqueID()));
            return res;
        }else if (request instanceof RemoveServerRequest res) {
            people.removeServer(res.getId());
            return res;
        }else if (request instanceof AddPersonToServerRequest res) {
            people.addPersonToServer(res.getUserName(),res.getServerUniqueID());
            return res;
        }else if (request instanceof GetMemberServerListRequest res) {
            res.setUsers(people.getServerMembers(res.getServerUniqueID()));
            return res;
        }else if (request instanceof RemovePersonFromServerRequest res) {
            people.removePersonFromServer(res.getUserName(),res.getServerUniqueID());
            return res;
        } else if (request instanceof AddRoleToServerRequest res) {
            people.addRoleToServer(res.getRole(),res.getServerUniqueID());
            return res;
        } else if (request instanceof GetServerRolesRequest res) {
            res.setRoles(people.getServerRoles(res.getServerID()));
            return res;
        }else if (request instanceof ChangeServerNameRequest res) {
            people.changeServerName(res.getName(),res.getServerUniqueID());
            return res;
        }else if (request instanceof CheckIsPersonExistInServerRequest res) {
            res.setResult(people.isPersonExistInServer(res.getUserName(),res.getServerID()));
            return res;
        }else if (request instanceof ChangePasswordRequest res) {
            res.setResult(people.changePersonPassword(res.getUsername(),res.getCurrentPassword(),res.getNewPassword()));
            return res;
        } else if (request instanceof UploadFileRequest res) {
            res.setFileID(people.uploadFile(res.getFile().getBytes(),res.getFile().getExtension()));
            return res;
        }else if (request instanceof DownloadFileRequest res) {
            res.setFile(people.downloadFile(res.getId()));
            return res;
        } else if (request instanceof AddRoleToPersonServerRequest res) {
            people.addRoleToPerson(res.getPersonUserName(), res.getRoleName(), res.getServerUniqueID());
            return res;
        } else if (request instanceof RemoveRoleFromPersonServerRequest res) {
            people.removeRoleFromPerson(res.getPersonUserName(), res.getRoleName(), res.getServerUniqueID());
            return res;
        } else if (request instanceof GetRoleMembersRequest res) {
            res.setUsers(people.getServerRoleMembers(res.getRoleName(), res.getServerUniqueID()));
            return res;
        } else if (request instanceof RestrictPersonFromAllServerRequest res) {
            people.restrictPersonFromAllServer(res.getUserName(), res.getServerUniqueID());
            return res;
        } else if (request instanceof RestrictPersonFromAChannelRequest res) {
            people.restrictPersonFromAChannel(res.getUserName(), res.getServerUniqueID(), res.getChannelName());
            return res;
        } else if (request instanceof RemoveRestrictPersonFromAllServerRequest res) {
            people.removeRestrictPersonFromAllServer(res.getUserName(), res.getServerUniqueID());
            return res;
        } else if (request instanceof RemoveRestrictPersonFromAChannelRequest res) {
            people.removeRestrictPersonFromAChannel(res.getUserName(), res.getServerUniqueID(), res.getChannelName());
            return res;
        } else if (request instanceof GetRestrictPersonsFromAllServerRequest res) {
            res.setList(people.getRestrictPersonsFromAllServer(res.getServerUniqueID()));
            return res;
        } else if (request instanceof GetRestrictPersonsFromAChannelRequest res) {
            res.setList(people.getRestrictPersonsFromAChannel(res.getServerUniqueID(), res.getChannelName()));
            return res;
        } else if (request instanceof UnRestrictAllRestrictPersonFromAChannelRequest res) {
            people.unRestrictAllRestrictPersonFromAChannel(res.getUserName(), res.getServerUniqueID(), res.getChannelName());
            return res;
        } else if (request instanceof GetPersonFreedomRequest res) {
            res.setChannels(people.getPersonFreemon(res.getUserName(), res.getServerID()));
            return res;
        } else if (request instanceof GetChannelMessagesRequest res) {
            res.setMessages(people.getChannelMessages(res.getChannelName(), res.getServerID(), res.getPersonID()));
            return res;
        } else if (request instanceof PinMessageToChannelRequest res) {
            people.pinMessageToChannel(res.getChannelName(), res.getServerID(), res.getMessage());
            return res;
        }else if (request instanceof GetPinedMessagesRequest res) {
            res.setMessages(people.getPinMessages(res.getChannelName(), res.getServerID()));
            return res;
        } else if (request instanceof ReactionToChannelMessageRequest res) {
            people.reactionToChannelMessage(res.getChannelName(), res.getServerID(), res.getMessage(),res.getReaction());
            return res;
        } else if (request instanceof SendChannelServerMessageRequest res) {
            people.sendChannelMessage(res.getChannel(), res.getServerID(), res.getText(), res.getSender());
            return res;
        } else if (request instanceof GetUpdatesRequest res) {
            res.setUpdate(people.getUpdates(res.getUserName()));
            return res;
        } else if (request instanceof GetServerImageRequest res) {
            res.setServerImage(people.getServerImageID(res.getServerID()));
            return res;
        } else if (request instanceof SetServerImageRequest res) {
            people.setServerImage(res.getServerID(), res.getImageID());
            return res;
        } else if (request instanceof CheckEmailAvailabilityRequest res) {
            res.setAvailable(people.checkEmailAvailability(res.getEmail()));
            return res;
        }else if (request instanceof ChangeEmailRequest res) {
            people.changePersonEmail(res.getUsername(), res.getNewMail());
            return res;
        } else if (request instanceof ChangePhoneNumberRequest res) {
            people.changePhoneNumber(res.getUsername(), res.getPhoneNumber());
            return res;
        } else {
            return null;
        }
        //endregion
    }
}
