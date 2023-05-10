import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginModule } from './login/login.module';
import { AlunoModule } from './aluno/aluno.module';
import { EmpresaModule } from './empresa/empresa.module';
import { HeaderComponent } from './shared/components/header/header.component';
import { TransacoesModule } from './transacoes/transacoes.module';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    LoginModule,
    AlunoModule,
    EmpresaModule,
    TransacoesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
