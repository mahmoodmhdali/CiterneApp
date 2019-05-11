import {AfterViewInit, Component, OnDestroy, OnInit} from '@angular/core';
import {NavigationService} from '../../../shared/services/navigation.service';
import {ThemeService} from '../../services/theme.service';
import {Subscription} from 'rxjs';
import {GlobalService} from '../../services/global.service';
import {UserProfileModel} from '../../models/UserProfile.model';

// import PerfectScrollbar from 'perfect-scrollbar';

@Component({
  selector: 'app-sidebar-side',
  templateUrl: './sidebar-side.component.html'
})
export class SidebarSideComponent implements OnInit, OnDestroy, AfterViewInit {
  // private sidebarPS: PerfectScrollbar;
  public menuItems: any[];
  public hasIconTypeMenuItem: boolean;
  public iconTypeMenuTitle: string;
  private menuItemsSub: Subscription;
  public logedInUser: UserProfileModel;

  constructor(private navService: NavigationService,
              public themeService: ThemeService,
              private globalService: GlobalService) {
  }

  ngOnInit() {
    this.logedInUser = this.globalService.getSession('loggedInUser');
    this.iconTypeMenuTitle = this.navService.iconTypeMenuTitle;
    this.menuItemsSub = this.navService.menuItems$.subscribe(menuItem => {
      this.menuItems = menuItem;
      // Checks item list has any icon type.
      this.hasIconTypeMenuItem = !!this.menuItems.filter(item => item.type === 'icon').length;
    });
  }

  ngAfterViewInit() {
    // setTimeout(() => {
    //   this.sidebarPS = new PerfectScrollbar('#scroll-area', {
    //     suppressScrollX: true
    //   })
    // })
  }

  ngOnDestroy() {
    // if(this.sidebarPS) {
    //   this.sidebarPS.destroy();
    // }
    if (this.menuItemsSub) {
      this.menuItemsSub.unsubscribe();
    }
  }

}
