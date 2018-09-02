import {HttpClient} from '@angular/common/http';
import {ConfigLoader} from '@ngx-config/core';
import {ConfigHttpLoader} from '@ngx-config/http-loader';

export function configLoaderFactory(http: HttpClient): ConfigLoader {
  return new ConfigHttpLoader(http, '/src/api/config.json');
}