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
" When editing a file, always jump to the last cursor position
autocmd BufReadPost * if line("'\"") > 0 && line ("'\"") <= line("$") | exe "normal! g'\"" | endif

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


