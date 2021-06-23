package com.sneydr.roomr_tenant.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.sneydr.roomr_tenant.Adapters.MessageRecyclerViewAdapter;
import com.sneydr.roomr_tenant.App.TextInput.NormalTextInput.FirstNameTextInput;
import com.sneydr.roomrv2.App.TextInput.NormalTextInput.MessageTextInput;
import com.sneydr.roomr_tenant.App.TextInput.TextInput;
import com.sneydr.roomr_tenant.Entities.Message.Message;
import com.sneydr.roomr_tenant.Entities.Message.MessageFactory;
import com.sneydr.roomr_tenant.Entities.Users.Tenant;
import com.sneydr.roomr_tenant.Network.JSONParser;
import com.sneydr.roomr_tenant.Network.Observers.TenantObserver;
import com.sneydr.roomr_tenant.R;
import com.sneydr.roomr_tenant.SocketIO.Callbacks.CallbackType;
import com.sneydr.roomr_tenant.SocketIO.Observers.DisconnectObserver;
import com.sneydr.roomr_tenant.SocketIO.Observers.JoinObserver;
import com.sneydr.roomr_tenant.SocketIO.Observers.MessageObserver;
import com.sneydr.roomr_tenant.SocketIO.SocketIO;
import com.sneydr.roomr_tenant.ViewModels.TenantViewModel;

import java.util.ArrayList;
import java.util.List;

public class TenantMessageFragment extends FragmentTemplate implements TenantObserver, JoinObserver, MessageObserver {


    private MessageRecyclerViewAdapter adapter;
    private RecyclerView rcyMessages;
    private MessageTextInput message;
    private ImageButton btnSend;

    private int houseId;
    private String authToken;

    private MessageFactory factory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        rcyMessages = view.findViewById(R.id.rcyMessages);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setStackFromEnd(true);
        rcyMessages.setLayoutManager(linearLayoutManager);

        message = new MessageTextInput(view, R.id.tilMessage, R.id.edtMessage);
        message.resetError();
        btnSend = view.findViewById(R.id.btnSend);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SocketIO socketIO = SocketIO.getInstance();
        socketIO.registerSocket("join", socketIO.getListener(CallbackType.onJoin, this));
        socketIO.registerSocket("message", socketIO.getListener(CallbackType.onMessage, this));
        socketIO.registerSocket("leave", socketIO.getListener(CallbackType.onDisconnect, this));
        socketIO.connect();
        message.resetError();
        TenantViewModel tenantViewModel = ViewModelProviders.of(this).get(TenantViewModel.class);
        tenantViewModel.getTenant(authToken, this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void onTenant(Tenant tenant) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                factory = new MessageFactory(tenant.getEmail(), tenant.getFullName(), "Tenant", tenant.getImageURL(), houseId);
                btnSend.setOnClickListener(onSend);
                Message joinMessage = factory.getMessage("Join Room");
                SocketIO socketIO = SocketIO.getInstance();
                socketIO.emitMessage("join", joinMessage);
            }
        });
    }



    View.OnClickListener onSend = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SocketIO socketIO = SocketIO.getInstance();
            message.invokeValidation();
            if (message.getError() != null) {
                YoYo.with(Techniques.Shake).playOn(message.getEditText());
            }
            else {
                socketIO.emitMessage("message", factory.getMessage(message.getText()));
                message.getEditText().setText("");
                message.resetError();
            }

        }
    };


    public TenantMessageFragment setHouseId(int houseId) {
        this.houseId = houseId;
        return this;
    }

    public TenantMessageFragment setAuthToken(String authToken) {
        this.authToken = authToken;
        return this;
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    @Override
    public void onPause() {
        super.onPause();
        if (factory != null){
            SocketIO socketIO = SocketIO.getInstance();
            rcyMessages.swapAdapter(new MessageRecyclerViewAdapter(context, new ArrayList<>(), "Tenant", factory.getEmail()), true);
            socketIO.emitMessage("leave", factory.getMessage("Leave room"));
            socketIO.disconnect();
            socketIO.clearSocket("join");
            socketIO.clearSocket("message");
            socketIO.clearSocket("leave");
        }
    }

    @Override
    public void onMessage(Message message) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (adapter != null) {
                    adapter.add(message);
                    rcyMessages.scrollToPosition(adapter.getItemCount() - 1);
                }
            }
        });
    }

    @Override
    public void onJoin(List<Message> messages) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (factory != null) {
                    adapter = new MessageRecyclerViewAdapter(getContext(), messages, "Tenant", factory.getEmail());
                    LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);
                    rcyMessages.setLayoutAnimation(animation);
                    rcyMessages.setAdapter(adapter);
                    rcyMessages.scrollToPosition(adapter.getItemCount() - 1);
                }
                else {
                    Toast.makeText(context, "Failed to load messages", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




}
