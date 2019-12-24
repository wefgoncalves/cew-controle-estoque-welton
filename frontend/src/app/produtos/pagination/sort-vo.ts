import { SortDirection } from './sort-direction';

export interface SortVO {
  property: string;
  direction: SortDirection;
  ignoreCase: boolean;
  nullHandling: string;
  descending: boolean;
  ascending: boolean;
}
