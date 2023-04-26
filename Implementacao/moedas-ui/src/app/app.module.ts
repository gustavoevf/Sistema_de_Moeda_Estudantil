import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginModule } from './login/login.module';
import { AlunoModule } from './aluno/aluno.module';
import { EmpresaModule } from './empresa/empresa.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    LoginModule,
    AlunoModule,
    EmpresaModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
