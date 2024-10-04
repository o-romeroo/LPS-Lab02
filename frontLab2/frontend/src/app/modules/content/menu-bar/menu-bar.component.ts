import { MatDialog } from '@angular/material/dialog';
import { Component, HostListener, ViewChild } from '@angular/core';
import { MatToolbar } from '@angular/material/toolbar';
import { DrawerService } from 'src/app/service/drawer/drawer.service';
import { NotificationService } from 'src/app/service/notifications/notifications.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.scss']
})
export class MenuBarComponent {

  isScrolled = false;
  showIcon!: boolean;
  @ViewChild('toolbar', { static: true }) toolbar!: MatToolbar;


  constructor(private drawerService: DrawerService,
    private notificationService: NotificationService,
    private matDialog: MatDialog,
    private router: Router,
  private matSnackBar : MatSnackBar
) { }

  ngAfterViewInit(): void {
    const toolbarHeight = this.toolbar._elementRef.nativeElement.offsetHeight;

    const contentDiv = document.querySelector('.content') as HTMLElement;
    if (contentDiv) {
      contentDiv.style.height = `calc(100vh - ${toolbarHeight}px)`;
    }
  }



  ngOnInit(): void {
    this.renderAccordingScreen();
  }

  logout(){
    localStorage.clear();
    this.router.navigate(['/login']);
    this.notificationService.usuarioLogoutEvent('paoskdpaoskd');
  }

  

  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    this.renderAccordingScreen();
  }

  renderAccordingScreen() {
    let screenSize = window.innerWidth;
    if (screenSize < 500) {
      this.showIcon = false;
    } else if (screenSize >= 500 && screenSize < 800) {
      this.showIcon = true;
    } else if (screenSize >= 800) {
      this.showIcon = true;
    }
  }

  toggleDrawer() {
    this.drawerService.toggleDrawer();
  }

  toggleDrawerIfMobile() {
    if (!this.showIcon)
    this.drawerService.toggleDrawer();
  }


}
