import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CookieService {
  /**
   * Percorre os cookies da aplicação e separa eles baseado na chave
   * @param keyCookie Pede a chave para encontrar o cookie
   * @returns Retorna o cookie encontrado
   */
  getCookies(keyCookie: string) {
    var cookie = document.cookie.split(';');
    var key = keyCookie + '=';

    // Inicia o token vazio
    let cookieValue = '';

    cookie.forEach((_item, counter) => {
      // Verifica se existe o cookie com o nome do token
      if (cookie[counter].indexOf(key) == 0) {
        // Se sim, percorre a substring e pega o valor do token
        cookieValue = cookie[counter].substring(
          key.length,
          cookie[counter].length
        );
      }
    });
    return cookieValue;
  }

  /**
   * Insere um cookie
   * @param keyCookie Pede a chave que o cookie tera
   * @param valueCookie Pede o valor que o cookie tera
   */
  setCookie(keyCookie: string, valueCookie: string) {
    document.cookie = keyCookie + '=' + valueCookie;
  }
}
