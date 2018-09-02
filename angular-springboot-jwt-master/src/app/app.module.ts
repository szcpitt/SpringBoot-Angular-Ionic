import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule, HttpClient} from '@angular/common/http';
import {AppRoutingModule} from './app-routing.module';
import { MaterialModule } from './material.module';

import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {HistoryComponent} from './history/history.component';
import { AddReminderComponent } from './add-reminder/add-reminder.component';
import { ReminderTableComponent } from './reminder-table/reminder-table.component';

import {ReminderService} from './services/reminder.service';
import {UserService} from './services/user.service';
import {AuthenticationService} from './services/authentication.service';
import {AuthGuard} from './guards/auth-guard.service';

import {StompConfig, StompService} from '@stomp/ng2-stompjs';
import {stompConfigFactory} from './factories/stomp-config.factory';
import {ConfigLoader, ConfigModule, ConfigService} from '@ngx-config/core';
import {configLoaderFactory} from './factories/config-loader.factory';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HistoryComponent,
    LoginComponent,
    AddReminderComponent,
    ReminderTableComponent
  ],
  imports: [
    ConfigModule.forRoot({
      provide: ConfigLoader,
      useFactory: configLoaderFactory,
      deps: [HttpClient]
    }),
    BrowserModule,
    FormsModule,
    HttpClientModule,
    MaterialModule,
    AppRoutingModule,
  ],
  providers: [
    AuthenticationService,
    UserService,
    AuthGuard,
    ReminderService,
    StompService,
    {
      provide: StompConfig,
      useFactory: stompConfigFactory,
      deps: [ConfigService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
