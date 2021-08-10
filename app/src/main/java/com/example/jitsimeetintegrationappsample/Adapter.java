package com.example.jitsimeetintegrationappsample;

import android.annotation.SuppressLint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;



import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<CallRoomModel> todoList;
    private CallRoomList callRoomList;

    public Adapter(CallRoomList callRoomList) {
        this.callRoomList = callRoomList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_room, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        System.out.println(position + " here is position --------");
        System.out.println(todoList.get(position).getRoomName());
        holder.roomName.setText(todoList.get(position).getRoomName());
        holder.userName.setText(todoList.get(position).getUserInfo().getDisplayName());

    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public void setTasks(List<CallRoomModel> infoList) {
        this.todoList = infoList;
    }

/*    public void deleteItem(int position) {
        //had to handle this case manually, for some reason, sometimes the position is 1 and the size is 1 as well, which triggers out of boundary error
        if(todoList.size() == position)
            position = position - 1;
        TaskInfo item = todoList.get(position);
        db.deleteTask(item.getId());
        todoList.remove(position);
        if (todoList.isEmpty())
            mainActivity.tv.setText("No tasks so far");
        notifyItemRemoved(position);
    }*/



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView roomName;
        TextView userName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roomName = itemView.findViewById(R.id.roomName);
            userName = itemView.findViewById(R.id.userName);
        }
    }
}