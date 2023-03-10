package com.pondoku.pondoku.habitEvents;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pondoku.pondoku.R;

import java.util.ArrayList;

public class HabitEventsAdapter extends ArrayAdapter<Event> {

    public static final String DELETE_EVENT = "DELETE_EVENT";

    private ArrayList<Event> events;
    private Context context;
    private FirebaseFirestore db;
    private FirebaseUser user;
    private String uid;
    private CollectionReference eventsReference;
    private String docName;

    public HabitEventsAdapter(Context context, ArrayList<Event> events) {
        super(context, 0, events);
        this.events = events;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        Event event = getItem(position); // Get the Events instance of the current item

        /*
        On How to create a functional layout for listview with buttons
        https://stackoverflow.com/questions/17525886/listview-with-add-and-delete-buttons-in-each-row-in-android
         */
        if (view == null) {
            // if view is null, inflate the layout
            view = LayoutInflater.from(context).inflate(R.layout.content_card_view_edit_delete, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.cardView = view.findViewById(R.id.card_view_edit_delete);
            viewHolder.deleteButton = view.findViewById(R.id.card_view_delete);
            viewHolder.editButton = view.findViewById(R.id.card_view_edit);
            view.setTag(viewHolder);
        } else {
            // else, use the previous one
            viewHolder = (ViewHolder) view.getTag();
        }

        // sets the event list name
        TextView eventTitle = view.findViewById(R.id.card_view_edit_delete_text);
        eventTitle.setText(event.getTitle());

        // event monitoring response part
        // sets a listener for the card view to show event details
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEventDetails(event);
            }
        });

        // sets a listener for the delete button
        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteClicked(event);
            }
        });

        // sets a listener for the edit button
        viewHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditClicked(event);
            }
        });
        return view;
    }

    /**
     * Internal class to use to save instances of the given variables
     */
    class ViewHolder {
        private FloatingActionButton editButton;
        private FloatingActionButton deleteButton;
        private CardView cardView;

    }

    /**
     * Helper method to set the database
     */
    private void setDatabase(Event event) {
        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        eventsReference = db.collection("Users").document(uid).collection("Events");

        // gets the doc id of the current event
        eventsReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                @Nullable FirebaseFirestoreException error) {
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    Event event2 = (Event) doc.toObject(Event.class);
                    if (event.getLatitude() == event2.getLatitude() &&
                            event.getLongitude() == event2.getLongitude() &&
                            event.getTitle().equals(event2.getTitle()) &&
                            event.getComment().equals(event2.getComment())) {

                        docName = doc.getId();
                    }
                }
            }
        });

    }

    /**
     * Method to show a event's details by going to another activity
     *
     * @param event An instance of event to be shown
     */
    private void showEventDetails(Event event) {
        Intent intent = new Intent(getContext(), ViewHabitEventsActivity.class);
        intent.putExtra(HabitEventsFragment.EVENT_TITLE, event.getTitle());
        intent.putExtra(HabitEventsFragment.EVENT_COMMENT, event.getComment());
        intent.putExtra(HabitEventsFragment.EVENT_LATITUDE, event.getLatitude());
        intent.putExtra(HabitEventsFragment.EVENT_LONGITUDE, event.getLongitude());
        intent.putExtra(HabitEventsFragment.EVENT_IMAGE, event.getImageName());
        context.startActivity(intent);
    }

    /**
     * Method to show an alert dialog to the user to confirm deletion of event
     *
     * @param event An instance of Habit habit to be deleted
     */
    private void onDeleteClicked(Event event) {
        setDatabase(event); // set an instance of the database
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setMessage("Are you sure you want to delete " + event.getTitle() + "?");
        alertDialog.setPositiveButton("Delete", (dialog, id) -> {
            deleteHabitEvents(docName);
            dialog.cancel();
        });
        alertDialog.setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());

        AlertDialog dialogBuilder = alertDialog.create();
        dialogBuilder.show();
        dialogBuilder.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.red_main));
        dialogBuilder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.red_main));
    }

    /**
     * method to delete an event
     *
     * @param docName the document to be deleted
     */
    private void deleteHabitEvents(String docName) {

        eventsReference
                .document(docName)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(DELETE_EVENT, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(DELETE_EVENT, "Error Deleting document", e);
                    }
                });
        this.notifyDataSetChanged();
    }


    /**
     * Method to go to another activity to let the user edit a given event
     *
     * @param event An instance of Event to be edited
     */
    private void onEditClicked(Event event) {

        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        eventsReference = db.collection("Users").document(uid).collection("Events");

        eventsReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                @Nullable FirebaseFirestoreException error) {
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    Event event2 = (Event) doc.toObject(Event.class);
                    if (event.getLatitude() == event2.getLatitude() &&
                            event.getLongitude() == event2.getLongitude() &&
                            event.getTitle().equals(event2.getTitle()) &&
                            event.getComment().equals(event2.getComment())) {

                        docName = doc.getId();
                    }
                }
            }
        });

        Intent intent = new Intent(getContext(), HabitsEventsEditActivity.class);
        intent.putExtra(HabitEventsFragment.EVENT_TITLE, event.getTitle());
        intent.putExtra(HabitEventsFragment.EVENT_COMMENT, event.getComment());
        intent.putExtra(HabitEventsFragment.EVENT_LATITUDE, event.getLatitude());
        intent.putExtra(HabitEventsFragment.EVENT_LONGITUDE, event.getLongitude());
        intent.putExtra(HabitEventsFragment.EVENT_IMAGE, event.getImageName());
        intent.putExtra(HabitEventsFragment.EVENT_OBJECT, event);
        context.startActivity(intent);
    }

}
