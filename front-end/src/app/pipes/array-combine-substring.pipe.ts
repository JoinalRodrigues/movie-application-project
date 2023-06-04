import { Pipe, PipeTransform } from '@angular/core';
import { Role } from '../service/admin.service';

@Pipe({
  name: 'arrayCombineSubstring'
})
export class ArrayCombineSubstringPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    let rolesString = (value as Role[]).map(i => i.name.substring(5)).reduce((t, i) => t = t + i + ',', '');
    return rolesString.substring(0, rolesString.length - 1);
    // if(args.length == 1)
    //   return (value as string).substring(args[0] as number);
    // if(args.length == 2)
    //   return (value as string).substring(args[0] as number, args[1] as number);
    // else
    //   return null;
  }

}
