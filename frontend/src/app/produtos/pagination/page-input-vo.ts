import { SortVO } from './sort-vo';

export interface PageInputVO {
  pageNumber: number;
  size: number;
  sort: SortVO[];
  totalElements: number;
  changedQuery: boolean;
}
