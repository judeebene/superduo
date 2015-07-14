package barqsoft.footballscores.widget;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import barqsoft.footballscores.MainActivity;
import barqsoft.footballscores.R;
import barqsoft.footballscores.service.FootballRemoteViewsService;

/**
 * Created by Jude Ben on 7/14/2015.
 */
public class FootballWidget extends AppWidgetProvider {

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.football_widget);

            Intent footballServiceIntent = new Intent(context, FootballRemoteViewsService.class);
            footballServiceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
            footballServiceIntent.setData(Uri.parse(footballServiceIntent.toUri(Intent.URI_INTENT_SCHEME)));

            remoteViews.setRemoteAdapter(R.id.widget_listview, footballServiceIntent);


            Intent footballTemplate = new Intent(context, MainActivity.class);
            footballTemplate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            PendingIntent footballPendingIntent = PendingIntent.getActivity(context, 0, footballTemplate, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setPendingIntentTemplate(R.id.widget_listview, footballPendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

        }
        super.onUpdate(context,appWidgetManager,appWidgetIds);
    }
}
