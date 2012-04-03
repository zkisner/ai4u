" vim 700: set foldmethod=marker:
"
" LastChanged:  20/01/08
" Version:      1.1
" Credits:      Misha Seltzer.

" Section: Basic Settings {{{1

" Section: General Settings {{{2
set nocompatible " Vim, not vi.
set backspace=indent,eol,start
set autowrite
set autoindent " always set autoindenting on
set shell=bash
set directory=/var/$USER/vim
filetype plugin indent on

" Section: Visual Settings {{{2
set cpoptions+=$
set laststatus=2
set showmatch
set tabstop=2
set smarttab
set expandtab
set bg=dark
set wildmode=longest,list
set shortmess=aI
syntax on
set hidden
set ruler       " show the cursor position all the time
set showcmd     " display incomplete commands
set textwidth=80
autocmd FileType java setlocal textwidth=100
autocmd BufRead,BufNewFile *.swig setlocal syntax=cpp

" Section: Search Settings {{{3
set nohlsearch
set incsearch       " do incremental searching
nnoremap <F1> :set hls!<CR>

" Section: Format Settings {{{2
set formatoptions=cql
set shiftwidth=2

" Section: Manipulations {{{2
map <F4> :vsplit<CR>
map <F5> :bp<CR>
map <F6> :bn<CR>
map <F12> :bd<CR>

" Section: Copy Paste {{{2
map <S-Insert> "*P<Right>"
imap <S-Insert> <C-R>*
cmap <S-Insert> <C-R>*

vnoremap p <ESC>:let current_reg = @"<CR>gvdi<C-R>=current_reg<CR><ESC>
set pastetoggle=<F8>

" cursor restore commands

set viminfo='100,\"100,:20,%,n~/.viminfo

function! ResCur()
  if line("'\"") <= line("$")
    normal! g`"
    return 1
  endif
endfunction

augroup resCur
  autocmd!
  autocmd BufWinEnter * call ResCur()
augroup END

" perforce commands
command! -nargs=* -complete=file PEdit :!g4 edit %
command! -nargs=* -complete=file PRevert :!g4 revert %
command! -nargs=* -complete=file PDiff :!g4 diff %

function! s:CheckOutFile()
 if filereadable(expand("%")) && ! filewritable(expand("%"))
   let option = confirm("Readonly file, do you want to checkout from p4?"
         \, "&Yes\n&No", 1, "Question")
   if option == 1
     PEdit
   endif
   edit!
 endif
endfunction
au FileChangedRO * nested :call <SID>CheckOutFile()

