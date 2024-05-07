import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[DecimalNumber]'
})
export class DecimalnumberDirective {
  input: number = 0;
  private regex: RegExp;
  constructor(private el: ElementRef) { }
  prec: number = 3;

  @Input()
  set DecimalNumber(value: string) {
    if (value !== null && value !== "") {
      this.input = parseInt(value);
    } else {
      this.input = 0;
    }
  }

  @HostListener('keydown', ['$event'])
  onKeyDown(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Tab' || event.key === 'ArrowLeft' || event.key === 'ArrowRight') {
      return;
    }
    this.regex = new RegExp(`^[0-9]{0,${this.input}}(\\.[0-9]{0,3})?$`);
    const currentValue: string = this.el.nativeElement.value;
    const nextValue: string = currentValue.concat(event.key);
    if (nextValue && !String(nextValue).match(this.regex)) {
      event.preventDefault();
    }
  }
}